<template>
  <div class="Echarts">
    <el-card shadow="always" style="margin-bottom: 10px">
      <span style="font-weight:bold"> 2023年度余额充值月份统计图 </span>
      <div id="main" style="width: 1300px; height: 600px"></div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Echarts",
  data() {
    return {
        number:[],
    };
  },
  mounted() {
    this.getNumber();
  },
  methods: {
    async getNumber() { 
      // 这里使用async关键字
      await this.$axios
        .get("http://127.0.0.1:8080/vipData/BalanceNumber") // 这里使用await关键字
        .then((resp) => {
          if (resp.data.code == 200) {
            this.number = resp.data.data;
          }
        });
      this.myEcharts(); // 这里在数据获取完成后执行myEcharts()方法
    },
    myEcharts() {
      var chartDom = document.getElementById("main");
      var myChart = this.$echarts.init(chartDom);
      var option;

      option = {
        xAxis: {
          type: "category",
          data: ["1月", "2月", "3月", "4月", "5月", "6月", "7月","8月","9月","10月","11月","12月"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: this.number,
            type: "line",
          },
        ],
      };

      option && myChart.setOption(option);
    },
  },
};
</script>

<style>
</style>

