import router from './router'
import store from './store'
import {Message} from 'element-ui'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import {getToken} from '@/utils/auth'
import {isPathMatch} from '@/utils/validate'
import {isRelogin} from '@/utils/request'

NProgress.configure({showSpinner: false})

const whiteList = ['/login', '/register', '/front/home']

const isWhiteList = (path) => {
  return whiteList.some(pattern => isPathMatch(pattern, path))
}

router.beforeEach((to, from, next) => {
  NProgress.start()
  if (getToken()) {
    to.meta.title && store.dispatch('settings/setTitle', to.meta.title)
    /* has token*/
    if (to.path === '/login') {
      next({path: '/'})
      NProgress.done()
    } else if (isWhiteList(to.path)) {
      next()
    } else {
      if (store.getters.roles.length === 0) {
        isRelogin.show = true
        // 判断当前用户是否已拉取完user_info信息
        store.dispatch('GetInfo').then((res) => {
          console.log('res.roles:', res.roles); // 打印角色信息
          const commonIndex = res.roles.indexOf('common');
          console.log('common 角色的索引:', commonIndex); // 打印 'common' 角色的索引
          isRelogin.show = false
          store.dispatch('GenerateRoutes').then(accessRoutes => {
            // 根据roles权限生成可访问的路由表
            router.addRoutes(accessRoutes) // 动态添加可访问路由表

            let path = to.path;
            if (res.roles.indexOf('common') > -1) {
              path = '/front/home'
              console.log('已修改path为home');
            }
            else if (to.path === '/index') {
              path = '/'
              console.log('已修改path为/');
            }

            console.log('最终跳转路径:', path);
            next({path, replace: true});
            console.log('已调用 next 进行跳转');

          })
        }).catch(err => {
          store.dispatch('LogOut').then(() => {
            Message.error(err)
            next({path: '/'})
          })
        })
      } else {
        next()
      }
    }
  } else {
    // 没有token
    if (isWhiteList(to.path)) {
      // 在免登录白名单，直接进入
      next()
    } else {
      next(`/login?redirect=${encodeURIComponent(to.fullPath)}`) // 否则全部重定向到登录页
      NProgress.done()
    }
  }
})

router.afterEach(() => {
  NProgress.done()
})
