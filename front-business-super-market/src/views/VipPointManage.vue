<template>
  <div>
    <!-- 消费金额和积分映射关系 -->
    <el-card shadow="always" style="margin-bottom: 10px">
      <el-alert
        style="text-align: left"
        :closable="false"
        title="积分映射管理"
        description="消费金额转换成相应积分的映射关系管理"
        show-icon
      >
      </el-alert>

      <el-form style="margin-top: 10px">
        <el-row :gutter="50">
          <el-col :span="5">
            <el-form-item label="消费金额" style="text-align: center">
              <el-input v-model="mappingAmountPoint.amount"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="1" style="margin-top:40px">
            <el-form-item style="text-align: center;size:20px">
                /
            </el-form-item>
          </el-col>

          <el-col :span="5">
            <el-form-item label="积分" style="text-align: center">
              <el-input v-model="mappingAmountPoint.point"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="3" style="margin-top: 40px;margin-left:-50px">
            <el-button plain type="info" @click="calculateRatio">=</el-button>
          </el-col>

          <el-col :span="5" style="margin-top: 0px;margin-left:-50px">
            <el-form-item label="换算比" style="text-align: center">
              <el-input v-model="ratio" disabled></el-input>
            </el-form-item>
          </el-col>
          
          <button
          style="margin-top: 40px;margin-left:-250px"
            type="button"
            @click="updateAmountPointRatio()"
            class="css-button-rounded--green"
          >
            确认修改
          </button>
        </el-row>
      </el-form>

    </el-card>

    <!-- 积分和会员等级映射关系 -->
    <el-card shadow="always" style="margin-bottom: 10px">
      <el-alert
        style="text-align: left"
        :closable="false"
        title="会员等级映射管理"
        description="积分和会员等级映射关系管理"
        show-icon
      >
      </el-alert>

      <div style="margin-top: 10px">
        <table border="1" cellpadding="10" cellspacing="0" width="100%">
          <tr>
            <th>会员等级</th>
            <th>所需积分</th>
            <th>优惠</th>
          </tr>
          <tr v-for="(item, index) in levels" :key="index">
            <td>
              {{ item.name }}<img :src="item.icon" width="20" height="20" />
            </td>
            <td>{{ item.min }} - {{ item.max }}</td>
            <td>{{ item.discount }}%</td>
          </tr>
        </table>
      </div>
    </el-card>

    <!-- 会员等级设置 -->
    <el-card shadow="always" style="margin-bottom: 10px">
      <el-alert
        style="text-align: left"
        :closable="false"
        title="会员等级设置"
        description="会员等级数量设置"
        show-icon
      >
      </el-alert>

      <div
        style="
          margin-top: 10px;
          display: flex;
          align-items: center;
          justify-content: center;
        "
      >
        <p>会员等级数量：</p>
        <input
          type="range"
          min="2"
          max="5"
          v-model.number="levelCount"
          @change="changeLevelCount"
        />
        {{ levelCount }}
      </div>

      <div
        style="
          margin-top: 10px;
          display: flex;
          align-items: center;
          justify-content: center;
        "
      >
        当前会员等级：{{ levels[levelCount - 2].name
        }}<img :src="levels[levelCount - 2].icon" width="20" height="20" />
      </div>
    </el-card>
  </div>
</template>
  
  <script>
export default {
  data() {
    return {
      mappingAmountPoint: { amount: "", point: "" },
      ratio: "",
      levelCount: 2,
      levels: [
        {
          name: "vip1:普通会员",
          icon: "normal.png",
          min: 0,
          max: 999,
          discount: 95,
        },
        {
          name: "vip2:银牌会员",
          icon: "silver.png",
          min: 1000,
          max: 4999,
          discount: 90,
        },
        {
          name: "vip3:金牌会员",
          icon: "gold.png",
          min: 5000,
          max: 9999,
          discount: 85,
        },
        {
          name: "vip4:钻石会员",
          icon: "diamond.png",
          min: 10000,
          max: 19999,
          discount: 80,
        },
        {
          name: "vip5:至尊会员",
          icon: "supreme.png",
          min: 20000,
          max: Infinity,
          discount: 75,
        },
      ],
    };
  },
  methods: {
    // 计算消费金额和积分之间的转换率
    calculateRatio() {
      if (this.mappingAmountPoint.amount && this.mappingAmountPoint.point) {
        this.ratio = (
          this.mappingAmountPoint.amount / this.mappingAmountPoint.point
        ).toFixed(2);
      } else {
        this.ratio = "";
      }
    },
    changeLevelCount() {
      this.levelCount = parseInt(this.levelCount);
    },
    // 修改数据库中所有的积分
    updateAmountPointRatio(){
        this.$axios.post("http://127.0.0.1:8080/vip/updateAmountPointRatio/"+this.ratio)
        .then(resp => {
            if(resp.data.code == 200){
                this.$message.success("修改成功")
            }
        })
    },
  },
};
</script>  
