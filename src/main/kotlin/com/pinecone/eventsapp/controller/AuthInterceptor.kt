package com.pinecone.eventsapp.controller

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import org.springframework.stereotype.Service
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Service
class AuthInterceptor : HandlerInterceptorAdapter() {


    @Throws(Exception::class)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
//        if (getController(ALGUM_CONTROLLER::class.java, handler) != null) {
//            return true
//        }

        val token = request.getHeader("Access-Token")
        return try {
            FirebaseAuth.getInstance().verifyIdToken(token)
            true
        } catch (e: FirebaseAuthException) {
            response.sendError(401, e.message)
            false
        }
    }

    private fun <T> getController(clazz: Class<T>, handler: Any): T? {
        if (handler is HandlerMethod) {
            val bean = handler.bean
            if (clazz.isInstance(bean)) {
                return bean as T
            }
        }
        return null
    }
}
