<template>
  <el-container>
    <!-- 侧边栏 -->
    <el-aside width="auto">
      <el-menu  router unique-opened default-active="/welcome" class="el-menu-vertical-demo"
      text-color="#fff" active-text-color="#ffd04b" :collapse="isCollapse" >
        <!-- 主页 -->
        <el-menu-item index="/welcome" class="showImage" style="height: 66px">
          <img />
        </el-menu-item>
        <!-- <el-submenu v-for=" menu in menus" :key="menu.mid" :index="menu.mid+''">
          <template slot="title"><i class="el-icon-message"></i>
            {{menu.mname}}
          </template>
          <el-menu-item-group>
            <el-menu-item v-for="submenu in menu.children" :key="submenu.mid" :index="submenu.mpath+''">
              {{submenu.mname}}
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu> -->
        <template v-for="menu in menus">
            <el-submenu :key="menu.mid" :index="menu.mid + ''">
                <template slot="title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="el-icon-discount"></i>{{ menu.mname }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</template>
                <el-menu-item-group>
                    <template v-for="submenu in menu.children">
                        <el-submenu v-if="submenu.children && submenu.children.length > 0" :key="submenu.mid"
                            :index="submenu.mid + ''">
                            <template slot="title"><i class="el-icon-collection-tag"></i>{{ submenu.mname }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</template>
                            
                            <el-menu-item v-for="grandchild in submenu.children" :key="grandchild.mid"
                                :index="grandchild.mpath + ''">
                                &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;{{ grandchild.mname }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </el-menu-item>
                        </el-submenu>
                        <el-menu-item v-else :key="submenu.mid" :index="submenu.mpath + ''">
                            {{ submenu.mname }}
                        </el-menu-item>
                    </template>
                </el-menu-item-group>
            </el-submenu>
        </template>


        <!-- 表格 -->
        <!-- <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-apple"></i>
            <span>表格</span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          </template>
          <el-menu-item index="/commonTable1">
            <template slot="title">
              <span slot="title" index="/person">原生版</span>
            </template>
          </el-menu-item>

          <el-menu-item index="/commonTable2">
            <template slot="title">
              <span slot="title">朴素版</span>
            </template>
          </el-menu-item>

          <el-menu-item index="/commonTable4">
            <template slot="title">
              <span slot="title">鲜艳版</span>
            </template>
          </el-menu-item>

          <el-menu-item index="/commonTable3">
            <template slot="title">
              <span slot="title">3D版</span>
            </template>
          </el-menu-item>
        </el-submenu> -->

        <!-- 表单 -->
        <!-- <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-cherry"></i>
            <span slot="title">表单</span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          </template>

          <el-menu-item index="/commonForm1">
            <template slot="title">
              <span slot="title">基础表单1</span>
            </template>
          </el-menu-item>

          <el-menu-item index="/commonForm2">
            <template slot="title">
              <span slot="title">基础表单2</span>
            </template>
          </el-menu-item>
        </el-submenu> -->

        <!-- 系统管理  -->
        <!-- <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-cherry"></i>
            <span slot="title">系统管理</span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          </template>

          <el-menu-item index="/menuList">
            <template slot="title">
              <span slot="title">菜单管理</span>
            </template>
          </el-menu-item>

          <el-menu-item index="/roleList">
            <template slot="title">
              <span slot="title">角色管理</span>
            </template>
          </el-menu-item>

          <el-menu-item index="/permissionList">
            <template slot="title">
              <span slot="title">权限管理</span>
            </template>
          </el-menu-item>

          <el-menu-item index="/userList">
            <template slot="title">
              <span slot="title">用户管理</span>
            </template>
          </el-menu-item>
        </el-submenu> -->
        <!-- 获取一级二级菜单-->
        <!-- <div> -->
          <!-- <el-container> -->
            <!-- <el-container>
              <el-aside width="200px" style="background-color: rgb(238, 241, 246)">-->
                <!-- <el-menu :default-openeds="['1,2,3']" :router="true">  -->
                  <!-- <el-submenu v-for=" menu in menus" :key="menu.mid" :index="menu.mid+''">
                    <template slot="title"><i class="el-icon-message"></i>
                      {{menu.mname}}
                    </template>
                    <el-menu-item-group>
                      <el-menu-item v-for="submenu in menu.children" :key="submenu.mid" :index="submenu.mpath+''">
                        {{submenu.mname}}
                      </el-menu-item>
                    </el-menu-item-group>
                  </el-submenu> -->
                <!-- </el-menu> -->
              <!-- </el-aside>
              <el-main>
                <router-view />
              </el-main>
            </el-container> -->
          <!-- </el-container> -->
        <!-- </div> -->
      </el-menu>
    </el-aside>
    <el-container style="margin: 0px">
      <!-- 顶部导航栏 -->
      <el-header
        style="background-color: white; margin: 0px; padding: 0px; height: 60px"
      >
        <el-card shadow="always" style="padding: 0; margin: 0; height: 110%">
          <div style="text-align: left; margin-top: -5px">
            <!-- 侧边栏收缩按钮 -->
            <el-button
              @click="changeButton"
              size="small"
              style="background: white;back text-align: left; "
            >
              <i class="el-icon-s-fold" style="font-size: 15px"></i>
            </el-button>

            <!-- 面包屑导航 -->
            <div style="height: 15px; display: inline-block; margin-left: 25px;">
              <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-top:3px">
                <el-breadcrumb-item
                  v-for="(item, index) in breadcrumb"
                  :key="index"
                >
                  <router-link
                    v-if="index !== breadcrumb.length - 1"
                    :to="item.path"
                    >{{ item.name }}</router-link
                  >
                  <span v-else>{{ item.name }}</span>
                </el-breadcrumb-item>
              </el-breadcrumb>
            </div>

            <div style="margin-left: 55%; display: inline-block"></div>
            <!-- 搜索按钮 -->
            <el-button
              size="small"
              @click="refreshButton"
              style="background: white; text-align: right;height=100%"
            >
              <i class="el-icon-search" style="font-size: 15px"></i>
            </el-button>
            <!-- 刷新按钮 -->
            <el-button
              @click="refreshButton"
              style="background: white; text-align: right"
              size="small"
            >
              <i class="el-icon-refresh" style="font-size: 15px"></i>
            </el-button>
            <!-- 全屏按钮 -->
            <el-button
              size="small"
              style="background: white; text-align: right"
              id="fullscreen-button"
            >
              <i class="el-icon-full-screen" style="font-size: 15px"></i>
            </el-button>
            <!-- 清除缓存按钮 -->
            <el-button
              size="small"
              @click="deleteButton"
              style="background: white; text-align: right"
            >
              <i class="el-icon-delete" style="font-size: 15px"></i>
            </el-button>
            <!-- 用户中心按钮 -->
            <el-button
              size="small"
              @click="excilButton"
              style="background: white; text-align: right"
            >
              <i class="el-icon-user" style="font-size: 15px"></i>
            </el-button>
          </div>
        </el-card>
      </el-header>
      <!-- 内容区域 -->
      <el-main style="margin-left: 0px; margin-top: 0px">
        <!-- 路由视图 -->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      //改变侧边栏的收缩
      isCollapse: false,
      // 设置路由
      breadcrumb: [
        { name: "首页", path: "/" },
        { name: "表格", path: "/commonTable1" },
        { name: "原生版", path: "/commonTable1" },
      ],
      menus: [], 
    };
  },
  created() {
    this.getMenus();
  },
  methods: {
    getMenus() {
      this.$axios.get("http://localhost:8080/menu/getMenus")
      .then((resp) => {
        if (resp.data.code === 200) {
          this.menus = resp.data.data;
        }
      });
    },
    // 退出登录
    excilButton() {
      // window.location.href = "http://localhost:8080/login.html"
      this.$message({
        message: "退出登录",
        type: "success",
      });
    },
    // 控制侧边栏收缩
    changeButton() {
      this.isCollapse = !this.isCollapse;
    },
    // 刷新页面
    refreshButton() {
      // 使用location.reload方法来重新加载页面，并忽略缓存内容
      location.reload(true);
      this.$message({
        message: "刷新成功",
        type: "success",
      });
    },
    // 清除服务端缓存
    deleteButton() {
      this.$message({
        message: "服务端清除缓存成功",
        type: "success",
      });
    },
  },
};
</script>


<style>
/* 选择器 .el-menu-item.is-active 表示选中的菜单项 */
.el-menu-item.is-active {
  /* 设置背景颜色为蓝色 */
  background-color: darkturquoise !important;
  /* 设置字体颜色为白色 */
  color: white !important;
}
.el-submenu:hover {
  /* 设置背景颜色为绿色 */
  background-color: blcak !important;
  color: black !important;
}
.el-menu-item:hover {
  /* 设置背景颜色为绿色 */
  background-color: darkturquoise !important;
  color: white !important;
}

.el-aside {
  /* 设置高度为100%，让侧边栏能够占满整个高度 */
  height: 100vh !important;
}
/* 选择器 .el-menu 表示整个侧边栏菜单 */
.el-menu {
  /* 设置背景颜色为偏黑色，可以根据自己的喜好调整颜色值 */
  background-color: #333 !important;
  border-right: none !important;
}
/* 侧边栏顶部展示图片 */
.showImage {
  background-image: url("../assets/title.png");
  background-size: cover;
  width: auto;
  height: auto;
}
/* 侧边栏收缩时，右侧自动扩充收缩 */
.el-container {
  width: auto;
}
</style>