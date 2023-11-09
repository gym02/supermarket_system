<template>
  <div>
    <!------------------------------ 商品数据搜索框 --------------------------------------------->
 
    <el-card shadow="always" style="margin-bottom: 10px;">
      <div style="margin-bottom: 5px">
        <el-form ref="form" size="small" label-width="200px" style="margin-top: 23px">
          <el-row :gutter="20" style="margin-left:-100px">
            <el-col :span="6" >
              <el-form-item label="门店">
                <el-select v-model="shopProduct.mdId" clearable placeholder="请选择">
                  <el-option
                    v-for="shop in shops"
                    :key="shop.mdId"
                    :label="shop.mdName"
                    :value="shop.mdId"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="20">
            <el-col :span="2" style="margin-left: 15px">
              <div>
                <button
                  type="button"
                  size="small"
                  @click="search()"
                  class="css-button-rounded--green"
                >
                  <i class="el-icon-search"></i>&nbsp;搜索
                </button>
              </div>
            </el-col>
            <el-col :span="1">
              <div>
                <button
                  type="success"
                  size="small"
                  @click="reset()"
                  class="css-button-rounded--green"
                >
                  <i class="el-icon-refresh"></i>&nbsp;重置
                </button>
              </div>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-card>
    <!-------------------------------------- 商品数据显示表 ------------------------------------>
    <el-card shadow="always">
      <!-- <div style="text-align: left; margin-bottom: 15px">
        <button type="primary" size="small" @click="selectShop()" class="css-button-rounded--blue">
          <i class="el-icon-plus"></i>&nbsp;商品上架
        </button>
        <span style="text-align: right; float: right; margin: 15px">
          <el-button size="small" plain type="info">
            <i class="el-icon-s-operation"></i>
          </el-button>
        </span>
      </div> -->
      <div>
        <el-table
          ref="multipleTable"
          :data="pageBean.beanList"
          tooltip-effect="dark"
          style="width: 100%"
          :default-sort="{ product: 'descending' }"
          :header-cell-style="{
          background: '#F5F7FA',
          'text-align': 'center',
          color: '#606266',
        }"
          stripe
          highlight-current-row
          @selection-change="handleSelectionChange"
          @row-click="handleRowClick"
        >
          <el-table-column type="selection" width="55" class="myRedCheckBox"></el-table-column>
          <el-table-column
            align="center"
            type="index"
            label="序号"
            width="180"
            show-overflow-tooltip
            sortable
            prop="spId"
            fixed
          ></el-table-column>
          <el-table-column
            align="center"
            label="商品名称"
            width="180"
            show-overflow-tooltip
            sortable
            prop="spName"
            fixed
          ></el-table-column>
          <el-table-column
            align="center"
            label="数量"
            width="180"
            show-overflow-tooltip
            sortable
            prop="putCount"
            fixed
          ></el-table-column>
          <el-table-column
            align="center"
            label="单位"
            width="180"
            show-overflow-tooltip
            sortable
            prop="unit"
            fixed
          ></el-table-column>
          <el-table-column
            align="center"
            label="零售价"
            width="180"
            show-overflow-tooltip
            sortable
            prop="spPrice"
            fixed
          ></el-table-column>
          <el-table-column
            align="center"
            label="生产日期"
            width="180"
            show-overflow-tooltip
            sortable
            prop="spProduceDate"
            fixed
          ></el-table-column>
          <el-table-column label="图片" width="280" show-overflow-tooltip sortable>
            <template slot-scope="scope">
              <img
                :src="'http://localhost:8080/images/'+scope.row.image"
                width="260px"
                height="180px"
              />
            </template>
          </el-table-column>
          <el-table-column label="商品状态" width="80">
            <template slot-scope="scope">
              <span v-if="scope.row.status==1">上架中</span>
              <span v-if="scope.row.status==2">已下架</span>
              <span v-if="scope.row.status==3">补货中</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" fixed="right" align="center">
            <template slot-scope="scope">
              <button
                size="small"
                plain
                type="warning"
                @click="put(scope.$index, scope.row)"
                class="css-button-rounded--yellow"
              >
                <i class="el-icon-edit"></i>&nbsp;重新上架
              </button>
            </template>
          </el-table-column>
        </el-table>
        <div>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[2, 4, 6]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pageBean.totalRecord"
          ></el-pagination>
        </div>
      </div>
    </el-card>
   
  </div>
</template>
<script>
export default {
  data() {
    return {
      activeName: "first",
      singledialogVisible: false,
      // 对话框添加或删除标记
      addFlag: true,
      // 存储修改行的下标
      updateIndex: "",
      kinds: [],
      kind: {
        zlId: "",
        zlName: ""
      },
      shops: [],
      shop: {
        mdId: "",
        mdName: ""
      },
      //表格显示数据，与后端进行绑定
      products: [],
      product: {
        spId: "",
        zlId: "",
        zlName: "",
        spName: "",
        suId: "",
        suName: "",
        spPrice: "",
        spImage: "",
        spProduceDate: "",
        pageNum: "",
        pageSize: "",
        putNo: "",
        spIds: []
      },
      formLabelWidth: "120px",
      supplies: [],
      // 显示添加对话框
      dialogVisible: false,
      // 添加员工绑定属性
      supply: {
        suId: "",
        suName: "",
        suContactor: "",
        suPhone: ""
      },
      // 搜索门店商品绑定属性
      shopProduct: {
        mdId: 1,
        spId: "",
        spName: "",
        putNo: "",
        putDate: "",
        putCount:"",
        zlId: "",
        zlName: "",
        spImage: "",
        spProduceDate: "",
        unit:'',
        spPrice:"",
        status:""
      },
      // 多选框选中的数据，删除时传输后台这个
      multipleSelection: [],
      // 临时变量，记录多选框删除数据
      selectedRows: [],
      // 选中当前行高亮
      currentRow: null,
      pageNum: 1,
      pageSize: 6,
      pageBean: {}
    };
  },
  mounted() {
    this.search();
    this.searchAllKinds();
    this.searchAllSupplies();
    this.searchAllShop();
  },
  methods: {
  
    putSingele() {
      console.log(this.product.mdId);
      console.log(this.product.spId);
      console.log(this.product.putNo);

      this.$axios
        .post("http://localhost:8080/shopProduct/add", this.product)
        .then(r => {
          if (r.data.code === 200) {
            this.$message.success("添加成功");
          } else {
            this.$message.error("添加失败");
          }
        });
      this.singledialogVisible = false;
    },
    put(index, row) {
       this.$confirm("此操作将重新上架商品, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          console.log(row)
          this.$axios
            .post("http://localhost:8080/shopProduct/putProductBySpId/" + row.spId+"/"+row.mdId)
            .then(r => {
              if (r.data.code === 200) {
                this.$message.success("上架成功");
                this.search();
              } else {
                this.$message.error("上架失败");
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消上架"
          });
        });
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val;
      this.search();
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.search();
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      // if (!isLt2M) {
      //     this.$message.error('上传头像图片大小不能超过 20MB!');
      // }
      return isJPG;
    },
    handleAvatarSuccess(res, file) {
      console.log(res);
      this.product.spImage = res.data;
      console.log(this.product.spImage);
    },
    // 上架商品

    // 取消添加人员
    cancle() {
      this.dialogVisible = false;
      // 这样清空真的很快，不用傻乎乎每个属性都赋空
      this.product = {};
    },
    cancle2() {
      this.singledialogVisible = false;
      // 这样清空真的很快，不用傻乎乎每个属性都赋空
      this.product = {};
    },
    //点击一行也加入多选框
    handleRowClick(row, column, event) {
      // 点击某一行时，切换该行的勾选状态，并更新selectedRows数组
      this.$refs.multipleTable.toggleRowSelection(row);
      let index = this.selectedRows.indexOf(row);
      if (index > -1) {
        this.selectedRows.splice(index, 1);
      } else {
        this.selectedRows.push(row);
      }
    },
    // 用于保存选中的行
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    search() {
      console.log(this.shopProduct);
      this.shopProduct.status = 2;
      console.log(this.shopProduct.status);
      this.$axios
        .post(
          "http://localhost:8080/shopProduct/getAll/" +
            this.pageNum +
            "/" +
            this.pageSize,
          this.shopProduct
        )
        .then(r => {
          if (r.data.code === 200) {
            this.pageBean = r.data.data;
            console.log(this.pageBean);
          }
        });
    },
    selectShop() {
      if (this.multipleSelection.length == 0) {
        this.$message.error("请至少选中一条数据");
        return;
      }
      this.dialogVisible = true;
    },
    // 批量添加商品
    putAll() {
      for (let m of this.multipleSelection) {
        this.product.spIds.push(m.spId);
      }
      this.$axios
        .post("http://localhost:8080/shopProduct/putAll", this.product)
        .then(r => {
          if (r.data.code === 200) {
            this.$message.success("添加成功");
            this.search();
            this.product.mdId = "";
            this.product.putNo = "";
          } else {
            this.$message.error("添加失败");
          }
          this.dialogVisible = false;
        });
    },
    // 搜索种类
    searchAllKinds() {
      this.$axios.get("http://localhost:8080/kind/getAllKinds").then(r => {
        if (r.data.code === 200) {
          this.kinds = r.data.data;
        }
      });
    },
    //搜索供货商
    searchAllSupplies() {
      this.$axios.get("http://localhost:8080/supply/getAllSupplies").then(r => {
        if (r.data.code === 200) {
          this.supplies = r.data.data;
        }
      });
    },
    //搜索所有门店
    searchAllShop() {
      this.$axios.get("http://localhost:8080/shop/getAllShops").then(r => {
        if (r.data.code === 200) {
          this.shops = r.data.data;
        }
      });
    },
    // 搜索重置
    reset() {
      this.shopProduct = {};
      this.$message({
        type: "success",
        message: "重置成功"
      });
    },

    // 部门标签相关方法
    formatter(row, column) {
      return row.department;
    },
    // 部门标签相关方法
    filterTag(value, row) {
      return row.department === value;
    },
    // 部门标签相关方法
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    }
  }
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

/* 绿色按钮 */
.css-button-rounded--green {
  min-width: 80px;
  height: 40px;
  color: #fff;
  padding: 5px 10px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  outline: none;
  border-radius: 5px;
  border: 2px solid white;
  background: #57cc99;
  margin: 3px;
}
.css-button-rounded--green:hover {
  background: #6ef2b9;
}
/* 红色按钮 */
.css-button-rounded--red {
  min-width: 80px;
  height: 40px;
  color: #fff;
  padding: 5px 10px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  outline: none;
  border-radius: 5px;
  border: 2px solid white;
  background: #f34646;
  margin: 3px;
}
.css-button-rounded--red:hover {
  background: #ff7e91;
}
/* 黄色按钮 */
.css-button-rounded--yellow {
  min-width: 80px;
  height: 40px;
  color: #fff;
  padding: 5px 10px;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  outline: none;
  border-radius: 5px;
  border: 2px solid white;
  background: #f5cd07;
  margin: 3px;
}
.css-button-rounded--yellow:hover {
  background: #ffe566;
}
</style>