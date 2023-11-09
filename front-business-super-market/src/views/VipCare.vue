<template>
  <div>
    <el-card shadow="always" style="margin-bottom: 10px;height:730px">
      <span style="margin-left:-20px;font-weight:bold" > 会员关怀 </span>
      <div class="card-content">
        <!-- 添加v-if属性和<Transition>组件 -->
        <transition name="el-fade-in-linear">
          <p v-if="showLine1">今天是 {{ date }}☀️，</p>
        </transition>

        <transition name="el-fade-in-linear">
          <p v-if="showLine2">{{ festival }}🌈。</p>
        </transition>

        <transition name="el-fade-in-linear">
          <p v-if="showLine3">
            商超系统共有 {{ totalMembers }} 名会员🤹‍♂️,
          </p>
        </transition>

        <transition name="el-fade-in-linear">
          <p v-if="showLine4">
          其中 {{ birthdayMembers }} 名会员今天过生日🎂。
          </p>
        </transition>

        <transition name="el-fade-in-linear">
          <p v-if="showLine5">已经为他们发送了祝福短信㊗️!</p>
        </transition>

        <transition name="el-fade-in-linear">
          <p v-if="showLine6"><img src="../assets/birthday.png"> </p>
        </transition>

      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      date: "",
      festival: "",
      totalMembers: "",
      birthdayMembers: "", // 添加三个布尔值来控制每一行文字的显示和隐藏
      showLine1: false,
      showLine2: false,
      showLine3: false,
      showLine4: false,
      showLine5: false,
      showLine6:false,
    };
  },
  created() {
    this.initData(); // 添加一个定时器来依次显示每一行文字
    setTimeout(() => {
      this.showLine1 = true;
    }, 1000);
    setTimeout(() => {
      this.showLine2 = true;
    }, 2000);
    setTimeout(() => {
      this.showLine3 = true;
    }, 3000);
    setTimeout(() => {
      this.showLine4 = true;
    }, 4000);
    setTimeout(() => {
      this.showLine5 = true;
    }, 5000);
    setTimeout(() => {
      this.showLine6 = true;
    }, 6000);
  },
  methods: {
    initData() {
      this.date = new Date().toLocaleDateString("zh-CN");
      this.$axios
        .get("http://127.0.0.1:8080/vipData/getVipCareData")
        .then((resp) => {
          if (resp.data.code == 200) {
            this.festival = resp.data.data[0];
            this.totalMembers = resp.data.data[1];
            this.birthdayMembers = resp.data.data[2];
            if (resp.data.data[0] == null) {
              this.festival = "普通但不平凡的一天";
            }
          }
        });
    },
  },
};
</script>
    <style>

</style>

