import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import CommonTable1 from '../views/CommonTable1.vue'
import CommonTable2 from '../views/CommonTable2.vue'
import CommonTable3 from '../views/CommonTable3.vue'
import CommonTable4 from '../views/CommonTable4.vue'
import CommonForm1 from '../views/CommonForm1.vue'
import CommonForm2 from '../views/CommonForm2.vue'
import Welcome from '../views/Welcome.vue'
//
import MenuList from '../views/MenuList.vue'
import UserList from '../views/UserList.vue'
import RoleList from '../views/RoleList.vue'
import PermissionList from '../views/PermissionList.vue'
//老谢
import ShopManagement from '../views/ShopManagement.vue'
import ProductManagement from '../views/ProductManagement.vue'
import SupplyManagement from '../views/SupplyManagement.vue'
import KindMangement from '../views/KindMangement.vue'
import ShopProductManagement from '../views/ShopProductManagement.vue'
import PutProduct from '../views/PutProduct.vue'
import PullProductManagement from '../views/PullProductManagement.vue'
// 老马
import EmployeeList from '../views/EmployeeList.vue'
import EmployeeAdd from '../views/EmployeeAdd.vue'
import Login  from '../views/Login.vue'
import FindPassword  from '../views/FindPassword.vue'
import GoodsList  from '../views/GoodsList.vue'
import GoodsAdd  from '../views/GoodsAdd.vue'
import Process from '../views/Process.vue'
import StoreList from '../views/StoreList.vue'
import Chart from '../views/Chart.vue'
import NewChart from '../views/NewChart.vue'
//老高
import VipAdd from '../views/VipAdd.vue'
import VipSelect from '../views/VipSelect.vue'
import VipFindPassword from '../views/VipFindPassword.vue'
import VipBalance from '../views/VipBalance.vue'
import VipPoint from '../views/VipPoint.vue'
import VipCare from '../views/VipCare.vue'
import VipBalanceDetail from '../views/VipBalanceDetail.vue'
import VipPointManage from '../views/VipPointManage.vue'
import VipPointDetail from '../views/VipPointDetail.vue' 
import VipPointAmountManage from '../views/VipPointAmountManage.vue' 
import VipPointLevelManage from '../views/VipPointLevelManage.vue'
import VipFestival from '../views/VipFestival.vue'
import VipIndent from '../views/VipIndent.vue' 
import Lottery from '../views/Lottery.vue'
import VipDataLevelCount from '../views/VipDataLevelCount.vue'
import VipDataBalanceCount from '../views/VipDataBalanceCount.vue' 
import VipDataPointCount from '../views/VipDataPointCount.vue'
//
import AfterSaleOrder from '../views/AfterSaleOrder.vue'
import RetailOrder from '../views/RetailOrder.vue'


Vue.use(VueRouter)
// 在重写push|replace方法时，先把VueRouter原型对象的push|replace保存一份
let originPush = VueRouter.prototype.push
let originReplace = VueRouter.prototype.replace
/**参数1：告诉原来的push | replace方法，你往那里跳转（传递哪些参数）、参数2：成功回调、 参数3：失败回调**/
VueRouter.prototype.push = function (location, resolve, reject) {
    if (resolve && reject) {
        originPush.call(this, location, resolve, reject)
    } else {
        originPush.call(this, location, () => { }, () => { })
    }
}
VueRouter.prototype.replace = function (location, resolve, reject) {
    if (resolve && reject) {
        originReplace.call(this, location, resolve, reject)
    } else {
        originReplace.call(this, location, () => { }, () => { })
    }
}


const routes = [
  {
    path:'/findPassword',
    name:'findPassword',
    component: FindPassword
  },
  { 
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/',
    redirect:'/home',
  },
  {
    path: '/home',
    name: 'home',
    component:Home,
    children:[
      {
        path: '/commonTable1',
        name: 'commonTable1',
        component: CommonTable1
      },
      {
        path: '/commonTable2',
        name: 'commonTable2',
        component: CommonTable2
      },
      {
        path: '/commonTable3',
        name: 'commonTable3',
        component: CommonTable3
      },
      {
        path: '/commonTable4',
        name: 'commonTable4',
        component: CommonTable4
      },
      {
        path: '/welcome',
        name: 'welcome',
        component: Welcome
      },
      {
        path: '/commonForm1',
        name: 'commonForm1',
        component: CommonForm1
      },
      {
        path: '/commonForm2',
        name: 'commonForm2',
        component: CommonForm2
      },

      {
        path: '/menuList',
        name: 'menuList',
        component: MenuList
      },
      {
        path: '/roleList',
        name: 'roleList',
        component: RoleList
      },
      {
        path: '/permissionList',
        name: 'permissionList',
        component: PermissionList
      },
      {
        path: '/userList',
        name: 'userList',
        component: UserList
      },
      //
      {
        path:'/newChart',
        name:'newChart',
        component:NewChart
      },
      {
        path:'/chart',
        name:'chart',
        component:Chart
      },
      {
        path:'/storeList',
        name:'storeList',
        component:StoreList
      },
      {
        path:'/process/:storeapplyId',
        name:'process',
        component:Process
      },
      {
        path:'/goodsAdd',
        name:'goodsAdd',
        component: GoodsAdd
      },
      {
        path:'/goodsList',
        name:'goodsList',
        component: GoodsList
      },
      {
        path: '/employeelist',
        name: 'employeelist',
        component: EmployeeList
      },
      {
        path: '/employeeAdd',
        name: 'employeeAdd',
        component: EmployeeAdd
      },

      {
        path: '/shopManagement',
        name: 'shopManagement',
        component: ShopManagement
      },
      {
        path: '/productManagement',
        name: 'productManagement',
        component: ProductManagement
      },
      {
        path: '/supplyManagement',
        name: 'supplyManagement',
        component: SupplyManagement
      },
      {
        path: '/kindMangement',
        name: 'kindMangement',
        component: KindMangement
      },
      {
        path: '/shopProductManagement',
        name: 'shopProductManagement',
        component: ShopProductManagement
      },
      {
        path: '/putProduct',
        name: 'putProduct',
        component: PutProduct
      },
      {
        path: '/pullProductManagement',
        name: 'pullProductManagement',
        component: PullProductManagement
      },



      {
        path: '/vipAdd',
        name: 'vipAdd',
        component: VipAdd
      },
      {
        path: '/vipSelect',
        name: 'vipSelect',
        component: VipSelect
      },
      {
        path: '/vipFindPassword',
        name: 'vipFindPassword',
        component: VipFindPassword
      },
      {
        path: '/vipBalance',
        name: 'vipBalance',
        component: VipBalance
      },
      {
        path: '/vipBalanceDetail',
        name: 'vipBalanceDetail',
        component: VipBalanceDetail
      },
      {
        path: '/vipPoint',
        name: 'vipPoint',
        component: VipPoint
      },
      {
        path: '/vipPointManage',
        name: 'vipPointManage',
        component: VipPointManage
      },
      {
        path: '/vipPointDetail',
        name: 'vipPointDetail',
        component: VipPointDetail
      },
      {
        path: '/vipPointAmountManage',
        name: 'vipPointAmountManage',
        component: VipPointAmountManage
      },
      {
        path: '/vipPointLevelManage',
        name: 'vipPointLevelManage',
        component: VipPointLevelManage
      },
      {
        path: '/vipIndent',
        name: 'vipIndent',
        component: VipIndent
      },
      {
        path: '/vipFestival',
        name: 'vipFestival',
        component: VipFestival
      },
      {
        path: '/vipCare',
        name: 'vipCare',
        component: VipCare
      },
      {
        path: '/vipDataLevelCount',
        name: 'vipDataLevelCount',
        component: VipDataLevelCount
      },
      {
        path: '/vipDataBalanceCount',
        name: 'vipDataBalanceCount',
        component: VipDataBalanceCount
      },
      {
        path: '/vipDataPointCount',
        name: 'vipDataPointCount',
        component: VipDataPointCount
      },
      {
        path: '/lottery',
        name: 'lottery',
        component: Lottery
      },

      {
        path: '/retailOrder',
        name: 'retailOrder',
        component: RetailOrder
      },{
        path: '/afterSaleOrde',
        name: 'afterSaleOrdeery',
        component: AfterSaleOrder
      },



    ]
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
