<template>
<div>
  <div class="Echarts">
    <div id="main" style="width: 600px;height:400px;"></div>
  </div>
  <button @click="getMap">123</button>
  </div>
</template>

<script>
export default {
  name: 'Echarts',
    data() {
        return{
            names:[],
            counts:[]
        }
    },
     created() {

    
  },
  methods:{
  async  getMap(){
        
    await   this.$axios
        .post(
          "http://localhost:8080/store/map" ,
        )
        .then((resp) => {
          if(resp.data.code==200){
          this.names = resp.data.data[0];
          this.counts = resp.data.data[1];
         
         
         
          ;}
          
        
         
        }); 
        this.myEcharts();
    },
	  myEcharts(){
            
		  // 基于准备好的dom，初始化echarts实例
		  var myChart = this.$echarts.init(document.getElementById('main'));
            
    

		  // 指定图表的配置项和数据
		  var option = {
			  title: {
				  text: 'ECharts 入门示例'
			  },
			  tooltip: {},
			  legend: {
				  data:['货物数量']
			  },
			  xAxis: {
				  data: this.names
			  },
			  yAxis: {},
			  series: [{
				  name: '货物数量',
				  type: 'bar',
				  data: this.counts
			  }]
		  };

		  // 使用刚指定的配置项和数据显示图表。
		  myChart.setOption(option);
		  }
  },
 
}
</script>

<style>

</style>