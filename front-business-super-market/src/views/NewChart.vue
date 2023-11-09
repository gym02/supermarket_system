<template>
  
  <div class="Echarts">
    
    <el-card shadow="always" style="margin-bottom: 10px">
    
      <span style="font-weight: bold">货物统计图 </span> 
    
      <div id="main" style="width: 1250px; height: 800px"></div>
      
    </el-card>

  </div>
</template>
 <script>
export default {
  name: "Echarts",
  data() {
    return {
      LevelCount: [],
    };
  },
  methods: {
    myEcharts() {
      // 基于准备好的dom，初始化echarts实例
      var chartDom = document.getElementById("main"); //   var myChart = this.$echarts.init(chartDom, 'dark');
      var myChart = this.$echarts.init(chartDom);
      var option; // 指定图表的配置项和数据

      option = {
        legend: {
          top: 550,
          right: "center",
          bottom: 0,
          left: 450,
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            restore: { show: true },
            saveAsImage: { show: true },
          },
        }, // 其他配置项
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)", // 这里使用formatter属性，{a}表示系列名，{b}表示数据名，{c}表示数据值，{d}表示百分比
        },
        series: [
          {
            name: "货物统计图",
            type: "pie",
            radius: [80, 200],
            center: ["50%", "50%"],
            roseType: "area",
            itemStyle: {
              borderRadius: 8,
            },
            data: this.LevelCount,
            label: {
              formatter: "{b}: {d}%", // 这里使用formatter属性，{b}表示数据名，{d}表示百分比
            },
          },
        ],
      }; // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
    }, // 查询数据 // 查询不同会员等级对应所用会员数
    async getLevelCount() {
        let storeDTO={}
      // 这里使用async关键字
      await this.$axios
         .post(
          "http://localhost:8080/store/count/" ,storeDTO
        ) // 这里使用await关键字
        .then((resp) => {
          if (resp.data.code == 200) {
            this.LevelCount = resp.data.data;

          }
        });
      this.myEcharts(); // 这里在数据获取完成后执行myEcharts()方法
    },
  },
  created() {
    this.getLevelCount();
  },
  mounted() {
    this.myEcharts();
  },
};
</script> 
 <style>
</style>