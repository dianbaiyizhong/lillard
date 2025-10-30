package com.nntk.nba.watch.lillard.http

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.UnknownHostException
import kotlin.math.pow

class RetryInterceptor(
    private val maxRetries: Int = 3,          // 最大重试次数
    private val initialDelayMs: Long = 1000,  // 初始延迟时间（毫秒）
    private val backoffMultiplier: Double = 2.0 // 退避乘数
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response: Response? = null
        var retryCount = 0
        var exception: IOException? = null

        while (retryCount <= maxRetries) {
            try {
                response = chain.proceed(request)

                // 只对服务器错误（5xx）或特定状态码重试
                if (response.isSuccessful || !shouldRetry(response.code)) {
                    return response
                }
                response.close()
            } catch (e: IOException) {
                // 不可重试的异常直接抛出
                if (!shouldRetry(e)) {
                    throw e
                }
                exception = e
            }

            // 计算延迟时间
            val delayMs = (initialDelayMs * backoffMultiplier.pow(retryCount.toDouble())).toLong()

            // 执行延迟
            try {
                Thread.sleep(delayMs)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                throw InterruptedIOException("Retry interrupted")
            }

            retryCount++
        }

        throw exception ?: IOException("Unknown error occurred after $maxRetries retries")
    }

    /** 判断是否应该重试 */
    private fun shouldRetry(exception: IOException): Boolean {
        return when (exception) {
            is InterruptedIOException -> false  // 线程中断不重试
            is UnknownHostException -> false    // DNS 解析失败不重试
            is ConnectionShutdownException -> true
            else -> true
        }
    }

    /** 根据 HTTP 状态码判断是否重试 */
    private fun shouldRetry(statusCode: Int): Boolean {
        return statusCode in 500..599 // 仅对服务器错误重试
    }
}

