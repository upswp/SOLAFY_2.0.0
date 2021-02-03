
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') },
      // user
      {
        path: "/FindPassword",
        name: "FindPassword",
        component: () => import("pages/user/FindPassword.vue"),
      
      },
      // my page
      {
        path: "/mypage",
        name: "MyPage",
        component: () => import("pages/mypage/MyPage.vue"),
      
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
