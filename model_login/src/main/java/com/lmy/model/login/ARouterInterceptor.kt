package com.lmy.model.login

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import lmy.com.utilslib.utils.ModelJumpCommon
import lmy.com.utilslib.utils.Utils

/**
 * ARouter interceptor
 * CreateDate:2019/4/22
 * Author:lmy
 */
@Interceptor(priority = 8)
class ARouterInterceptor :IInterceptor {
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
       val extraType =  postcard?.extra
        if (extraType == ModelJumpCommon.LOGIN_TYPE) {
           if (Utils.isLogin()) callback?.onContinue(postcard)
        }else{
            callback?.onContinue(postcard)
        }
    }

    override fun init(context: Context?) {
    }
}