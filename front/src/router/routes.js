
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') },
      // user
      {
        path: "/findpassword",
        name: "FindPassword",
        component: () => import("pages/user/FindPassword.vue"),
      
      },
      {
        path: "/successsignup",
        name: "SuccessSignup",
        component: () => import("pages/user/SuccessSignup.vue"),
      
      },
      {
        path: "/signup",
        name: "Signup",
        component: () => import("pages/user/Signup.vue"),
        path: "/errorpassword",
        name: "ErrorPassword",
        component: () => import("pages/user/ErrorPassword.vue"),
      
      },
      {
        path: "/sendemail",
        name: "SendEmail",
        component: () => import("pages/user/SendEmail.vue"),
      
      },
      {
        path: "/successpassword",
        name: "SuccessPassword",
        component: () => import("pages/user/SuccessPassword.vue"),
      },
      // my page
      {
        path: "/mypage",
        component: () => import("pages/mypage/MyPage.vue"),
        children: [
          {
            path: "",
            component: () => import("pages/mypage/Profile.vue"),
          },
          {
            path: "/mypage/profile",
            component: () => import("pages/mypage/Profile.vue"),
          },
          {
            path: "/mypage/notification",
            component: () => import("pages/mypage/Notification.vue"),
          },
          {
            path: "/mypage/myarticle",
            component: () => import("pages/mypage/MyArticle.vue"),
          },
          {
            path: "/mypage/bookmark",
            component: () => import("pages/mypage/Bookmark.vue"),
          },
          {
            path: "/mypage/withdrawal",
            component: () => import("pages/mypage/Withdrawal.vue"),
          },
        ]
      },
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
