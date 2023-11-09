<template >
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  data() {
    return {
      title: "Simple Admin",
    };
  },
  created() {
    document.title = this.title;
  },
};
</script>


<style>
BODY, .header, .main {
  margin: 0;
  padding: 0;
}

.el-aside,
.el-menu {
  height: 100%;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
