<template>
  <div>
    <div>
      <el-form
        ref="goodsVO"
        :model="goodsVO"
      
        label-width="80px"
        style="text-align: left"
      >
        <el-form-item label="货物种类">
          <el-select v-model="goodsVO.cargo.cargoId" placeholder="请选择">
            <el-option
              v-for="s in cargos"
              :key="s.cargoId"
              :label="s.cargoName"
              :value="s.cargoId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="货物数量" >
          <el-input
            v-model="goodsVO.goodsCount"
            placeholder="请输入数量"
          ></el-input>
        </el-form-item>
        <el-form-item label="单位">
          <el-input
            v-model="goodsVO.goodsUnit"
            placeholder="请输入单位"
          ></el-input>
        </el-form-item>

      
        <el-form-item label="申请时间">
          <el-date-picker
            v-model="goodsVO.goodsfromDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="经办人" >
          <el-input
            v-model="goodsVO.user.userName"
            placeholder="请输入单位"
            :disabled="true"
          ></el-input>
        </el-form-item>
      
       
      
    

        <el-form-item>
          <el-button type="primary" @click="addGoods"
            >添加</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>


export default {
  data() {
    // }

    return {
     
      
      
      goodsVO: { user: {  }, cargo: { } },
      cargos: [
      
      ],
      user:{}

   
    };
  },
  created() {
    this.getAllCargos();
    this.getUser();
  },
  methods: {
    getUser(){
        this.$axios
        .post("http://localhost:8080/goods/user")
        .then((resp) => {
          
          this.goodsVO.user=resp.data.data;
          this.user=resp.data.data
          
          console.log(this.goodsVO)
        });
        
       
    },
     handleChange(value) {
        console.log(value);
      },
    getAllCargos() {
      
      this.$axios
        .post("http://localhost:8080/goods/cargo")
        .then((resp) => {
          this.cargos = resp.data.data;
        });
    },
    addGoods() {
      
        
          this.$axios
            .post("http://localhost:8080/goods/add", this.goodsVO)
            .then((resp) => {
              if (resp.data.code == 200) {
                this.$message("添加成功");
                this.goodsVO = {
                 
                  user: this.user,
                  cargo:{}
                };
              } else if (resp.data.code == 100) {
                this.$message("验证失败" + JSON.stringify(resp.data.data));
              }
            });
       
     
    },
  },
};
</script>

<style>
</style>