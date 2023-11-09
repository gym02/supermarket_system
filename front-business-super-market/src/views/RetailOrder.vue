<template>
  <div>
    <div>零售订单</div>
    <!--------------------------------- 数据搜索框 ------------------------------------>
    <el-card shadow="always" style="margin-bottom: 10px">
      <div style="margin-bottom: 5px;width: 75%;">
        <el-form
          ref="form"
          :model="indentDTO"
          size="small"
          label-width="80px"
          style="margin-top: 23px"
        >
          <el-row :gutter="20">
            <el-col :span="6">
              <el-form-item label="订单号">
                <el-input v-model="indentDTO.ddId"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="商品名称">
                <el-input v-model="indentDTO.spName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="买家名称">
                <el-input v-model="indentDTO.mjName"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="14">
            <el-col :span="6">
              <el-form-item label="送货方式">
                <el-select v-model="indentDTO.sendWay.shId" placeholder="请选择">
                  <el-option key=null label="请选择" value=null />
                  <el-option
                    v-for="item in sendways"
                    :key="item.shId"
                    :label="item.shName"
                    :value="item.shId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="9">
              <el-form-item label="下单时间">
                <el-date-picker
                  v-model="ArrayDate"
                  type="daterange"
                  align="right"
                  unlink-panels
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>

            <el-col :span="2" style="margin-left: 15px">
              <div>
                <button
                  type="button"
                  size="small"
                  @click="search()"
                  class="css-button-rounded--blue"
                >
                  <i class="el-icon-search"></i>&nbsp;搜索
                </button>
              </div>
            </el-col>
            <el-col :span="2">
              <div>
                <button
                  type="button"
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
    <!--------------------------------- 数据显示表 ------------------------------------>
    <el-card shadow="always">
      <div>
        <!----------------------- 添加、删除、导入、导出 -------------------------------->
        <div style="text-align: left; margin-bottom: 15px">
          <button
            type="button"
            size="small"
            @click="addNewOrder()"
            class="css-button-rounded--blue"
          >
            <i class="el-icon-plus"></i>&nbsp;添加
          </button>
          <button
            type="button"
            size="small"
            @click="removeAll()"
            class="css-button-rounded--red"
          >
            <i class="el-icon-delete"></i>&nbsp;删除
          </button>
          <button
            type="button"
            size="small"
            @click="toExport()"
            class="css-button-rounded--export"
          >
            <i class="el-icon-upload2"></i>&nbsp;导出
          </button>
          <button
            type="button"
            size="small"
            @click="toChannel()"
            class="css-button-rounded--import"
          >
            <i class="el-icon-download"></i>&nbsp;导入
          </button>
          <span style="text-align: right; float: right; margin: 15px">
            <el-button size="small" plain type="info">
              <i class="el-icon-s-operation"></i>
            </el-button>
          </span>
        </div>
        <!---------------------------- table标签条 ------------------------------------>
        <template>
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="全部" name="allindent"></el-tab-pane>
          <el-tab-pane label="待付款" name="waitpay"></el-tab-pane>
          <el-tab-pane label="待发货" name="waitallot"></el-tab-pane>
          <el-tab-pane label="运输中" name="havesend"></el-tab-pane>
          <el-tab-pane label="已收货" name="haverecive"></el-tab-pane>
          <el-tab-pane label="已完成" name="havefinish"></el-tab-pane>
        </el-tabs>
      </template>
        <!------------------------------ 表格数据 ------------------------------------->
        <el-table
          border
          ref="multipleTable"
          :data="pageBean.beanList"
          tooltip-effect="dark"
          style="width: 100%;"
          height="500px" 
          :default-sort="{ order: 'descending' }"
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
          <el-table-column type="selection" width="55" class="myRedCheckBox">
          </el-table-column>
          <el-table-column
            align="center"
            prop="ddId"
            label="订单号"
            width="180"
            show-overflow-tooltip
            sortable
          >
            <template slot-scope="scope">{{ scope.row.ddId }}</template>
          </el-table-column>
          <el-table-column
            align="center"
            prop="productVO.spName"
            label="商品名"
            width="120"
            show-overflow-tooltip
            sortable
          >
            <template slot-scope="scope">{{ scope.row.productVO.spName }}</template>
          </el-table-column>

          <el-table-column label="商品图片" prop="productVO.spImage" align="center" width="120" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-button icon="el-icon-picture-outline" circle @click="getImage(scope.$index,scope.row)"></el-button>
            </template>
          </el-table-column>

          <!-- <el-table-column
            align="center"
            prop="productVO.spImage"
            label="商品图片"
            width="160"
            show-overflow-tooltip
          >
            <template slot-scope="scope">{{ scope.row.productVO.spImage }}</template>
          </el-table-column> -->

          <el-table-column
            align="center"
            prop="kind.zlName"
            label="商品类型"
            width="100"
            :filters="this.productKinds"
            :filter-method="filterKind"
            filter-placement="bottom-end"
          >
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.kind.zlName === '礼品' ? 'primary' : scope.row.kind.zlName === '保健品' ? 'primary' : 'success'"
                disable-transitions
                >{{ scope.row.kind.zlName }}</el-tag
              >
            </template>
          </el-table-column>

          <el-table-column
            align="center"
            prop="productVO.spPrice"
            label="商品单价"
            width="135"
            show-overflow-tooltip
          >
            <template slot-scope="scope">{{ scope.row.productVO.spPrice }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            prop="ddNum"
            label="购买数量"
            width="120"
            show-overflow-tooltip
            sortable
          >
            <template slot-scope="scope">{{ scope.row.ddNum }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            label="买家"
            prop="customer.gkName"
            width="120"
            show-overflow-tooltip
          >
            <template slot-scope="scope">{{ scope.row.customer.gkName }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            prop="ddPayway"
            label="支付方式"
            width="100"
            :filters="[
              { text: '微信', value: '微信' },
              { text: '支付宝', value: '支付宝' },
              { text: '网上银行', value: '网上银行' },
              { text: '线下支付', value: '线下支付' },
            ]"
            :filter-method="filterPayway"
            filter-placement="bottom-end"
          >
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.ddPayway === '网上银行' ? 'primary' : scope.row.ddPayway === '支付宝' ? 'primary' : 'success'"
                disable-transitions
                >{{ scope.row.ddPayway }}</el-tag
              >
            </template>
          </el-table-column>

          <el-table-column
            align="center"  
            prop="ddOrdertime"
            label="下单时间"
            width="160"
            show-overflow-tooltip
            sortable
          >
            <template slot-scope="scope">{{ scope.row.ddOrdertime }}</template>
          </el-table-column>

          <el-table-column
            align="center"
            prop="sendWay.shName"
            label="送货方式"
            width="135"
            show-overflow-tooltip
          >
            <template slot-scope="scope">{{ scope.row.sendWay.shName }}</template>
          </el-table-column>
          
          <el-table-column
            align="center"
            prop="ddStatus"
            label="交易状态"
            width="100"
            :filters="[
              { text: '买家下单', value: '买家下单' },
              { text: '付款成功', value: '付款成功' },
              { text: '商品出库', value: '商品出库' },
              { text: '确认收货', value: '确认收货' },
              { text: '订单完成', value: '订单完成' },
              { text: '退货换货', value: '退货换货' }
            ]"
            :filter-method="filterStatus"
            filter-placement="bottom-end"
          >
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.ddStatus === '退货换货' ? 'warning' : scope.row.ddStatus === '付款成功' ? 'success' : scope.row.ddStatus === '订单完成' ? 'success' : 'primary'"
                disable-transitions
                >{{ scope.row.ddStatus }}</el-tag
              >
            </template>
          </el-table-column>

          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <button size="small" plain type="button" @click="prouductDetail(scope.$index, scope.row)"
                class="css-button-fully-rounded--yellow">
                <i class="el-icon-tickets"></i>&nbsp;详情
              </button>

              <button size="small" plain type="button" prop="ddStatus" @click="payMoney(scope.$index, scope.row)"
                class="css-button-arrow--sky2" v-show="scope.row.ddStatus=='买家下单'">
                <i class="el-icon-box"></i>&nbsp;付款
              </button>
              <button size="small" plain type="button" prop="ddStatus" @click="confirmSend(scope.$index, scope.row)"
                class="css-button-arrow--sky1" v-show="scope.row.ddStatus=='付款成功'">
                <i class="el-icon-truck"></i>&nbsp;发货
              </button>
              <button size="small" plain type="button" prop="ddStatus" @click="materialFlow(scope.$index, scope.row)"
                class="css-button-arrow--sky" v-show="scope.row.ddStatus=='商品出库'">
                <i class="el-icon-box"></i>&nbsp;收货
              </button>
              <button size="small" plain type="button" prop="ddStatus" @click="confirmRecive(scope.$index, scope.row)"
                class="css-button-arrow--green" v-show="scope.row.ddStatus=='确认收货'">
                <i class="el-icon-circle-check"></i>&nbsp;完成
              </button>
              <button size="small" plain type="button" prop="ddStatus" @click="afterSale(scope.$index, scope.row)"
                class="css-button-arrow--gre" v-show="scope.row.ddStatus=='订单完成'">
                <i class="el-icon-chat-dot-round"></i>&nbsp;售后
              </button>
              <button size="small" plain type="button" prop="ddStatus" @click="deleteIndent(scope.$index, scope.row)"
                class="css-button-arrow--red" v-show="scope.row.ddStatus=='退货换货'">
                <i class="el-icon-delete"></i>&nbsp;删除
              </button>

            </template>
          </el-table-column>
        </el-table>
      </div>
      <!-------------------------------- 分页标签 ------------------------------------->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageBean.pageIndex"
        :page-sizes="[1, 2, 3, 10]"
        :page-size="pageBean.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pageBean.totalRecord"
      >
      </el-pagination>
    </el-card>
    <!-------------------------------- 订单详情对话框  --------------------------------->
    <el-dialog :visible.sync="dialogVisible" width="60%" :before-close="handleClose">
      <h3 style="text-align: left;">订单详情</h3><br>
      <el-steps :active="steps" align-center>
        <el-step title="买家下单" :description="xdDate" icon="el-icon-sold-out"></el-step>
        <el-step title="付款成功" :description="fkDate" icon="el-icon-check"></el-step>
        <el-step :title="title3" :description="fhDate" icon="el-icon-truck"></el-step>
        <el-step :title="title4" :description="shDate" icon="el-icon-circle-check"></el-step>
        <el-step title="订单完成" :description="wcDate" icon="el-icon-s-claim"></el-step>
      </el-steps>
      <br><br><br>
      <div style="height: 100px;">
        <div style="margin-right: 10%; text-align: right;font-size: 25px ;color: #2D8CF0">{{ msg }}</div>
        <br>
        <button type="button" size="small" @click="modifyFlow" class="css-button-rounded--blue"
        style="margin-left: 74%">
            <i class="el-icon-edit"></i>&nbsp;修改信息
        </button>
      </div>
      <h3 style="text-align: left;">收货信息</h3><br>
      <el-descriptions class="margin-top" :column="4" direction="vertical">
          <el-descriptions-item label="订单号">&nbsp;&nbsp;&nbsp;{{ orderId }}</el-descriptions-item>
          <el-descriptions-item label="交易状态" row>&nbsp;&nbsp;&nbsp;{{ orderType }}</el-descriptions-item>
          <el-descriptions-item label=""></el-descriptions-item><!-- 占位 -->
          <el-descriptions-item label=""></el-descriptions-item><!-- 占位 -->
          <el-descriptions-item label="收货人">&nbsp;&nbsp;&nbsp;{{ name }}</el-descriptions-item>
          <el-descriptions-item label="联系电话">&nbsp;&nbsp;&nbsp;{{ phone }}</el-descriptions-item>
          <el-descriptions-item label="收货地址">&nbsp;&nbsp;&nbsp;{{location }}</el-descriptions-item>
          <el-descriptions-item label="备注">&nbsp;&nbsp;&nbsp;{{ otherInfo }}</el-descriptions-item>
      </el-descriptions>
      
      <h3 style="text-align: left;">订单商品</h3><br>
      <el-table :data="detaildata" border style="width: 100%">
        <el-table-column prop="productVO.spName" label="商品名称" width="200"></el-table-column>
        <el-table-column prop="productVO.spImage" label="商品图片" width="200">
          <template slot-scope="scope">
              <el-button icon="el-icon-picture-outline" circle @click="getImage(scope.$index,scope.row)"></el-button>
          </template>
        </el-table-column>
        <el-table-column prop="productVO.spPrice" label="单价" width="180"></el-table-column>
        <el-table-column prop="ddNum" label="数量" width="180"></el-table-column>
        <el-table-column prop="allPrice" label="合计" width="180"></el-table-column>
        <el-table-column prop="ddType" label="状态" ></el-table-column>
      </el-table>
        <h3 style="text-align: right">商品总额：￥{{ productMoney }}</h3>
        <h3 style="text-align: right">运费：￥{{ yunFei }}</h3>
        <h2 style="text-align: right;color: rgba(255, 0, 0, 0.553)">金额：￥{{ allMoney }}</h2>
    </el-dialog>
    <!-------------------------------- 售后服务对话框  --------------------------------->
    <el-dialog :visible.sync="afterSaleDialog" width="40%" :before-close="handleClose">
      <h3 style="text-align: left;">售后服务</h3><br>
      <el-form ref="form" :model="afterSaleInfo" label-width="80px">
        <el-row :gutter="24">
          <el-col :span="12">
              <el-form-item label="售后服务">
                <el-select v-model="afterSaleInfo.asType" placeholder="请选择服务类型" @change="serviceChange()">
                  <el-option key=null label="请选择" value=null />
                  <el-option
                    v-for="item in afterSales"
                    :key="item.asId"
                    :label="item.asType"
                    :value="item.asType"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item :label="selectCase" v-show="this.afterSaleInfo.asType != ''">
                <el-select v-model="afterSaleInfo.asReason" placeholder="请选择">
                  <el-option key=null label="请选择" value=null />
                  <el-option
                    v-for="typ in afterSaleResons"
                    :key="typ.asId"
                    :label="typ.asReason"
                    :value="typ.asReason"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
        </el-row>

        <el-row :gutter="24">
          <el-col :span="12">
            <el-form-item label="退款说明" prop="desc">
              <el-input type="textarea" v-model="afterSaleInfo.asExplain"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle()">取 消</el-button>
        <el-button type="primary" @click="confirmAfterSale">确 定</el-button>
      </div>
    </el-dialog>
    <!-------------------------------- 添加信息对话框 ---------------------------------->
    <el-dialog title="添加订单" :visible.sync="addDialog" width="50%">
      <el-form ref="newOrder" :model="newOrder" label-width="80px" :rules="rules">
        <el-row :gutter="24">
          <el-col :span="8">
            <el-form-item label="商品类型" prop="kind.zlId">
              <el-select v-model="newOrder.kind.zlId" @change="selectAllProduct()">
                <el-option
                v-for="k in kinds"
                :key="k.zlId"
                :label="k.zlName"
                :value="k.zlId"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="商品名称" prop="product.spId">
              <el-select v-model="newOrder.product.spId" placeholder="请先选择商品类型" @change="selectPrice()">
                <el-option
                v-for="s in products"
                :key="s.spId"
                :label="s.spName"
                :value="s.spId"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8"></el-col>
        </el-row>

          <el-row :gutter="24">
            <el-col :span="8">
              <el-form-item label="商品单价">
                <el-input v-model="newOrder.product.spPrice" :disabled="true"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <template>
                <el-form-item label="商品数量">
                <el-input-number v-model="newOrder.num" 
                :min="1" :max="5000" @change="countPrice()"></el-input-number>
                </el-form-item>
              </template>
            </el-col>
            <el-col :span="8">
                
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="8">
              <el-form-item label="商品总价">
                <el-input v-model="newOrder.totalPrice" :disabled="true"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="8">
              <el-form-item label="买家名称" prop="name">
                <el-input v-model="newOrder.name"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="买家电话" prop="phone">
                <el-input v-model="newOrder.phone"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="24">
            <el-col :span="8">
              <el-form-item label="买家地址" prop="location">
                <el-input v-model="newOrder.location"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="送货方式" prop="sendWay.shId">
                <el-select v-model="newOrder.sendWay.shId" placeholder="请选择" @change="getFreight()">
                  <el-option key=null label="请选择" value=0 />
                  <el-option
                    v-for="item in sendways"
                    :key="item.shId"
                    :label="item.shName"
                    :value="item.shId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8"></el-col>
          </el-row>
          <el-row :gutter="24">
            <el-col :span="8">
              <el-form-item label="自提时间" v-show="show1">
                <el-date-picker
                  v-model="newOrder.delivertime"
                  type="date"
                  placeholder="选择日期"
                  value-format="yyyy-MM-dd"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="运费" v-show="show2">
                <el-input v-model="newOrder.freight" :disabled="true"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          
        <el-row :gutter="24">
        <el-col :span="24">
          <el-form-item label="备注信息">
            <el-input type="textarea" v-model="newOrder.otherInfo"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle()">取 消</el-button>
        <el-button type="primary" @click="confrimAddNewOrder">选择支付方式</el-button>
      </div>
    </el-dialog>
    <!------------------------------ 选择支付方式的对话框 --------------------------------->
    <el-dialog title="选择支付方式" :visible.sync="payWayDialog" width="300px">
      <el-form ref="newOrder" :model="newOrder" label-width="80px" :rules="rules">
            <el-form-item label="支付方式">
              <el-select v-model="newOrder.payWay">
                <el-option
                v-for="p in payWays"
                :key="p.payName"
                :label="p.payName"
                :value="p.payName"
                >
                </el-option>
              </el-select>
            </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle2()">取 消</el-button>
        <el-button type="primary" @click="confrimAddNewOrder2">添加</el-button>
      </div> 
    </el-dialog>
    <!------------------------------ 修改收货信息对话框 --------------------------------->
    <el-dialog title="修改收货信息" :visible.sync="modifyDialog">
      <el-form ref="form" :model="flowInfo" label-width="80px">
        <el-row :gutter="24 ">
          <el-col :span="12">
            <el-form-item label="订单号">
              <el-input v-model="flowInfo.orderId" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="交易状态">
              <el-input v-model="flowInfo.orderType" :disabled="true"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="24">
          <el-col :span="12">
            <el-form-item label="收货姓名">
              <el-input v-model="flowInfo.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话">
              <el-input v-model="flowInfo.phone"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="24">
          <el-col :span="24">
            <el-form-item label="收货地址">
              <el-input v-model="flowInfo.location"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row :gutter="24">
          <el-col :span="24">
            <el-form-item label="备注" prop="desc">
              <el-input type="textarea" v-model="flowInfo.otherInfo"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancle1()">取 消</el-button>
        <el-button type="primary" @click="confrimModifyFlow">确 定</el-button>
      </div>
    </el-dialog>
    <!-------------------------------显示图片的对话框------------------------------------->
	<el-dialog
			title="商品图片"
			:visible.sync="filed"
			width="30%">
		<el-image style="width: 300px; height: 300px" :src="path"></el-image>
		<span slot="footer" class="dialog-footer">
		    <el-button type="primary" @click="filed = false">关 闭</el-button>
		</span>
	</el-dialog>
  </div>
</template>
  
  <script>
export default {
  data() {
    return {
      filed: false,
      path:"",
      pageIndex: 1,
      pageSize: 10,
      pageBean: [],
      productKinds:[],
      ArrayDate:[],
      allIndent:{},
      activeName: 'allindent',
      sendways:[],
      kinds:[],
      products:[],
      detaildata:[],
      title3:"商品出库",
      title4:"自提完成",
      xdDate:"",
      fkDate:"",
      fhDate:"",
      shDate:"",
      wcDate:"",
      orderId:"",
      orderType:"",
      name:"",
      phone:"",
      location:"",
      otherInfo:"",
      selectCase:"退货原因",
      productMoney:null,
      yunFei:null,
      allMoney:null,
      msg:"",
      indentDTO:{
        sendWay:{shId:''},
        from:"",
        to:"",
      },
      afterSaleInfo:{
        ddid:"",
        asType:"",
        asReason:"",
        asExplain:"",
      },
      payWays:[
        { payId:1 ,payName:"微信" },
        { payId:2 ,payName:"支付宝" },
        { payId:3 ,payName:"线下支付" },
        { payId:4 ,payName:"网上银行" },
    ],
    afterSales:[
        { asId:1 ,asType:"换货" },
        { asId:2 ,asType:"仅退货" },
        { asId:3 ,asType:"退货退款" },
    ],
    afterSaleResons:[
        { asId:1 ,asType:"商品存在破损" },
        { asId:2 ,asType:"商品与卖家描述不符" },
        { asId:3 ,asType:"商品错发，漏发" },
        { asId:4 ,asType:"商品质量问题" },
        { asId:5 ,asType:"物流无跟踪进度" },
        { asId:6 ,asType:"未按约定时间发货" },
        { asId:7 ,asType:"七天无理由" },
        { asId:8 ,asType:"其他" },
    ],
    //新增订单字段
      newOrder:{
        kind: {zlId: null},
        product: {spId: null, spName: "", spPrice: null},
        delivertime:"",
        payWay:"",
        num:1,
        name:"",
        phone:"",
        sendWay: {shId: null, shName: ""},
        location:"",
        otherInfo:"",
        totalPrice: "",
        freight: 0,
      },
      //收件信息
      flowInfo:{
        orderId:'',
        orderType:'',
        name:'',
        phone:'',
        location:'',
        otherInfo:'',
      },
      afterSaleDialog:false,
      steps:0,
      // 对话框添加或删除标记
      addFlag: true,
      // 存储修改行的下标
      updateIndex: "",
      //表格显示数据，与后端进行绑定
      allIndent: {},
      // 显示添加对话框
      dialogVisible: false,
      addDialog:false,
      modifyDialog:false,
      payWayDialog:false,
      show1:false,
      show2:false,
      indent: {},
      //交易状态
      options: [],
      // 多选框选中的数据，删除时传输后台这个
      multipleSelection: [],
      // 临时变量，记录多选框删除数据
      selectedRows: [],
      // 选中当前行高亮
      currentRow: null,
      //具体规则
      rules: {
        'kind.zlId': [{ required: true, message: "请选择商品类型", trigger: "blur" },],
        'product.spId': [{ required: true, message: "请选择商品", trigger: "blur" }],
        name: [{ required: true, message: "请输入买家姓名", trigger: "blur" }],
        phone: [{ required: true, message: "请输入买家电话", trigger: "blur" }],
        location: [{ required: true, message: "请输入买家地址", trigger: "blur" }],
        'sendWay.shId': [{ required: true, message: "请选择送货方式", trigger: "blur" }],
      },
    };
  },
  created(){
    //获取全部订单信息
    this.getAllIndent();
    //获取全部送货方式
    this.getAllSendWays();
    //获取全部商品种类
    this.getAllSKinds();
  },
  methods: {
    //更改每页多少条信息
    handleSizeChange(val) {
      this.pageSize = val;
      this.getAllIndent();
    },
    //将当前页跳转到某一页axios
    handleCurrentChange(val) {
      this.pageIndex = val;
      this.getAllIndent();
    },
    //查询所有订单
    getAllIndent(){
      this.indentDTO.from=this.ArrayDate[0];
      this.indentDTO.to=this.ArrayDate[1];
      this.$axios
        .post(
          "http://localhost:8080/indent/get/" +this.pageIndex +"/" +this.pageSize,this.indentDTO)
        .then((resp) => {
          this.pageBean = resp.data.data;
        });
    },
    //标签页点击事件
    handleClick(tab, event) {
        if(tab.index==0){
          //全部
          this.indentDTO.ddStatus="";
          this.getAllIndent();
        }else if(tab.index==1){
          //待付款,买家下单
          this.indentDTO.ddStatus="买家下单";
          this.getAllIndent();
        }else if(tab.index==2){
          //待发货,付款成功
          this.indentDTO.ddStatus="付款成功";
          this.getAllIndent();
        }else if(tab.index==3){
          //运输中,商品出库
          this.indentDTO.ddStatus="商品出库";
          this.getAllIndent();
        }else if(tab.index==4){
          //已收货,确认收货
          this.indentDTO.ddStatus="确认收货";
          this.getAllIndent();
        }else if(tab.index==5){
          //已完成，订单完成
          this.indentDTO.ddStatus="订单完成";
          this.getAllIndent();
        }
    },
    //查询所有配送方式
    getAllSendWays(){
      this.$axios
      .get("http://localhost:8080/indent/sendWay/all")
      .then((resp) => {
        this.sendways = resp.data.data;
      });
    },
    //查询所有商品种类
    getAllSKinds(){
      this.$axios
      .get("http://localhost:8080/indent/kind/all")
      .then((resp) => {
        this.kinds = resp.data.data;
        let textArray=[];
        for(let i=0;i<this.kinds.length;i++){
        textArray.push(this.kinds[i].zlName)
      }
        this.productKinds = textArray.map(text => ({ text, value: text }));
      });
    },
    //获取图片路径
    getImage(index,row){
      this.filed=true; this.path = "http://localhost:8080/images/" + row.productVO.spImage;
    },
    //添加新订单的对话框
    addNewOrder() {
      this.addDialog=true
    },
    //根据种类去查找商品的名字
    selectAllProduct(){
      this.$axios
      .get("http://localhost:8080/indent/getAllProductName/" + this.newOrder.kind.zlId)
      .then((resp) => {
        this.products = resp.data.data;
      })
    },
    //根据商品名称去查找价格
    selectPrice(){
      this.$axios
      .get("http://localhost:8080/indent/getPriceByName/" + this.newOrder.product.spId)
      .then((resp) => {
        this.newOrder.product.spPrice=resp.data.data
        this.countPrice();
      })
    },
    //计算总价
    countPrice(){
      this.newOrder.totalPrice = this.newOrder.product.spPrice * this.newOrder.num
    },
    //获取运费
    getFreight(){
      if(this.newOrder.sendWay.shId == 0){
        this.newOrder.freight = 0
        this.show1=false;
        this.show2=false;
      }else if(this.newOrder.sendWay.shId == 1){
        this.newOrder.freight = 13
        this.show1=false;
        this.show2=true;
      }else if(this.newOrder.sendWay.shId == 2){
        this.newOrder.freight = 0;
        this.show2=false;
        this.show1=true;
      }else if(this.newOrder.sendWay.shId == 3){
        this.newOrder.freight = 5
        this.show1=false;
        this.show2=true;
      }else if(this.newOrder.sendWay.shId == 4){
        this.newOrder.freight = 8
        this.show1=false;
        this.show2=true;
      }
    },
    //选择支付方式
    confrimAddNewOrder(){
      this.$refs.newOrder.validate(valid => {
          if (valid) {
            this.payWayDialog=true;
          } else {
            return false;
          }
        })
    },
    //确认添加新订单
    confrimAddNewOrder2(){
      this.$refs.newOrder.validate(valid => {
          if (valid) {
            this.$axios
              .post("http://localhost:8080/indent/addNewOrder", this.newOrder)
              .then((resp) => {
                if (resp.data.code == 200) {
                  this.$message({
                    message: "添加成功",
                    type: "success",
                  });
                  //清空数据
                  this.newOrder={
                    kind: {zlId: null},
                    product: {spId: null, spName: "", spPrice: null},
                    delivertime:"",
                    num:1,
                    name:"",
                    phone:"",
                    sendWay: {shId: null, shName: ""},
                    location:"",
                    otherInfo:"",
                    totalPrice: "",
                    freight: 0,
                  }
                  this.getAllIndent();
                  this.addDialog=false;
                  this.payWayDialog=false;
                } else if (resp.data.code == 400) {
                  this.$message({
                    message: resp.data.data,
                    type: "error",
                  });
                }
              });
          } else {
            return false;
          }
        });
    },
    //导出订单
    toExport(){
      //意思是带条件查询所有订单，然后在后端接收请求后再做出下载的操作
      this.indentDTO.from=this.ArrayDate[0];
      this.indentDTO.to=this.ArrayDate[1];
      let url = "http://localhost:8080/indent/export";
        url+="/"+this.pageIndex;
        url+="/"+this.pageSize;

        /* if(this.indentDTO.sendWay.shId){
          url += "&sendWay.shId="+this.indentDTO.sendWay.shId;
        } */

        url += "?sendWay=" + encodeURIComponent(JSON.stringify(this.indentDTO.sendWay));

        if(this.indentDTO.ddId){
          url += "&ddId="+this.indentDTO.ddId;
        }

        if(this.indentDTO.spName){
          url += "&spName="+this.indentDTO.spName;
        }

        if(this.indentDTO.mjName){
          url += "&mjName="+this.indentDTO.mjName;
        }

        if(this.indentDTO.from){
          url += "&from="+this.indentDTO.from;
        }

         if(this.indentDTO.to){
          url += "&to="+this.indentDTO.to;
        }
         

        window.location.href = url;

    },
    //导入订单
    toChannel(){
      this.$message({
            type: "warning",
            message: "创作者正在努力开发......"
      })
    },
    //取消
    cancle() {
      this.$refs.newOrder.resetFields();
      this.afterSaleDialog=false;
      this.afterSaleInfo={
        ddid:"",
        asType:"",
        asReason:"",
        asExplain:"",
      };
      this.dialogVisible = false;
      this.addDialog=false;
      this.newOrder={
        kind: {zlId: null},
        product: {spId: null, spName: "", spPrice: null},
        payWay:"",
        delivertime:"",
        num:1,
        name:"",
        phone:"",
        sendWay: {shId: null, shName: ""},
        location:"",
        otherInfo:"",
        totalPrice: "",
        freight: 0,
      }
    },
    //修改信息取消
    cancle1(){
      this.modifyDialog=false;
      this.flowInfo={};
    },
    //关闭选择支付方式的对话框
    cancle2(){
      this.payWayDialog=false;
    },
    //关闭对话框
    handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
            this.detaildata=[];
          })
          .catch(_ => {});
    },
    //删除订单
    remove(index, row) {
      this.$confirm("此操作将删除信息, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "删除成功!",
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    //详情
    prouductDetail(index, row) {
      if(row.sendWay.shName=="上门自提"){
        this.title3="上门自提"
        this.title4="自提完成"
      }else{
        this.title3="商品出库"
        this.title4="确认收货"
      }
      //各阶段时间
      this.xdDate=row.xdDate;
      this.fkDate=row.fkDate;
      this.fhDate=row.fhDate;
      this.shDate=row.shDate;
      this.wcDate=row.wcDate;

      this.addFlag = false;
      this.indent = row;
      this.updateIndex = index;
      this.dialogVisible = true;
      //商品信息
      this.detaildata.push(row);
      this.detaildata[0].allPrice = this.detaildata[0].productVO.spPrice * this.detaildata[0].ddNum;
      //收货信息
      this.orderId=row.ddId;
      this.orderType=row.ddStatus;
      this.name=row.customer.gkName;
      this.phone=row.customer.gkPhone;
      this.location=row.customer.gkLocation;
      this.otherInfo=row.ddComment;
      //右下角金额
      this.productMoney=row.productVO.spPrice * row.ddNum;
      this.yunFei=row.ddYunfei;
      this.allMoney=row.productVO.spPrice * row.ddNum + row.ddYunfei;
      if(row.ddStatus=="买家下单"){
        this.steps=1;this.msg="待付款"
      }else if(row.ddStatus=="付款成功"){
        this.steps=2;this.msg="已付款"
      }else if(row.ddStatus=="商品出库"){
        this.steps=3;this.msg="已发货"
      }else if(row.ddStatus=="确认收货"){
        this.steps=4;this.msg="已收货"
      }else if(row.ddStatus=="订单完成"){
        this.steps=5;this.msg="已完成"
      }else if(row.ddStatus=="退货换货"){
        this.steps=5;this.msg="退换货"
      }

    },
    //详情界面的修改收货信息
    modifyFlow(){
      this.modifyDialog=true;
      this.flowInfo.orderId=this.orderId
      this.flowInfo.orderType=this.orderType
      this.flowInfo.name=this.name
      this.flowInfo.phone=this.phone
      this.flowInfo.location=this.location
      this.flowInfo.otherInfo=this.otherInfo
    },
    //确认修改详情页面的物流信息
    confrimModifyFlow(){
      this.$axios
          .post("http://localhost:8080/indent/modifyFlow" ,this.flowInfo)
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message({
                message: "修改成功",
                type: "success",
              });
              this.getAllIndent();
              this.modifyDialog = false;
              this.dialogVisible = false;
              this.payWayDialog =false;
            }else{
              this.$message({
                message: "未知异常",
                type: "error",
              });
            }
        })
    },
    //支付
    payMoney(index,row){
      this.$confirm("商品付款, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
            this.$axios
          .post("http://localhost:8080/indent/payMoney/"+row.ddId)
          .then((resp) => {
            this.sendways = resp.data.data;
            this.$message({
            type: "success",
            message: "付款成功"
          })
            this.getAllIndent();
          });
      }).catch(() => {
            this.$message({
              type: "info",
              message: "已取消付款",
            });
          });
    },
    //发货
    confirmSend(index,row){
      this.$confirm("商品发货, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
            this.$axios
          .post("http://localhost:8080/indent/deliverGoods/"+row.ddId)
          .then((resp) => {
            this.sendways = resp.data.data;
            this.$message({
            type: "success",
            message: "发货成功"
          })
            this.getAllIndent();
          });
      }).catch(() => {
            this.$message({
              type: "info",
              message: "已取消发货",
            });
          });
    },
    //收货
    materialFlow(index,row){
      this.$confirm("确认收货, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
            this.$axios
          .post("http://localhost:8080/indent/recivePackage/"+row.ddId)
          .then((resp) => {
            this.sendways = resp.data.data;
            this.$message({
            type: "success",
            message: "收货成功"
          })
            this.getAllIndent();
          });
      }).catch(() => {
            this.$message({
              type: "info",
              message: "已取消收货",
            });
          });
    },
    //确认交易完成
    confirmRecive(index,row){
      this.$confirm("确定无误, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$axios
          .get("http://localhost:8080/indent/finishIndent/" +row.ddId )
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message({
                message: "成功",
                type: "success",
              });
              this.getAllIndent();
            }else{
              this.$message({
                message: "未知异常",
                type: "error",
              });
            }
          
        })
      }).catch(() => {
        this.$message({
            type: "info",
            message: "已取消确认",
          });
      })

    },
    //售后服务
    afterSale(index,row){
      this.afterSaleDialog=true;
      this.afterSaleInfo.ddId = row.ddId;
    },
    //选择售后类型
    serviceChange(){
      if(this.afterSaleInfo.asType=="仅退款"){
        this.afterSaleResons=[
        { asId:1 ,asReason:"物流无跟踪进度" },
        { asId:2 ,asReason:"未按约定时间发货" },
        { asId:3 ,asReason:"七天无理由" },
        { asId:4 ,asReason:"不想要了" },
        { asId:5 ,asReason:"其他" },
        ];
      }else if(this.afterSaleInfo.asType=="换货"){
        this.afterSaleResons=[
        { asId:1 ,asReason:"商品存在破损" },
        { asId:2 ,asReason:"商品错发，漏发" },
        { asId:3 ,asReason:"商品质量问题" },
        { asId:4 ,asReason:"其他" },
        ];
      }else if(this.afterSaleInfo.asType=="退货退款"){
        this.afterSaleResons=[
        { asId:1 ,asReason:"商品存在破损" },
        { asId:2 ,asReason:"商品与卖家描述不符" },
        { asId:3 ,asReason:"商品错发，漏发" },
        { asId:4 ,asReason:"商品质量问题" },
        { asId:5 ,asReason:"物流无跟踪进度" },
        { asId:6 ,asReason:"未按约定时间发货" },
        { asId:7 ,asReason:"七天无理由" },
        { asId:8 ,asReason:"不想要了" },
        { asId:9 ,asReason:"其他" },
      ];
      }
    },
    //确定售后需求
    confirmAfterSale(){
      this.$confirm("确定发起申请, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$axios
          .post("http://localhost:8080/afterSale/changeAsType/" + this.afterSaleInfo )
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message({
                message: "申请成功",
                type: "success",
              });
              this.getAllIndent();
            }
          })
      }).catch(() => {
        this.$message({
            type: "info",
            message: "已取消确认",
          });
      })



    },
    //删除订单
    deleteIndent(index,row){
      this.$confirm("此操作将永久删除该行数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$axios
            .post("http://localhost:8080/indent/delete/" + row.ddId)
            .then((resp) => {
              if (resp.data.code == 200) {
                this.$message({
                  message: "删除成功",
                  type: "success",
                });
                this.getAllIndent();
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    // 删除所有人员
    removeAll() {
      this.$confirm("此操作将批量删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          console.log(this.multipleSelection);
          let val = this.multipleSelection; //选中的值
          let ids = [];
          if(val.length==0){
            this.$message.warning("没有选中数据");
            return;
          }
          for (let i = 0; i < val.length; i++) {
            ids.push(val[i].ddId);
          }
          this.$axios
          .post("http://localhost:8080/indent/removeBatch" , ids)
          .then((resp) => {
            if (resp.data.code == 200) {
              this.$message({
                message: "批量删除成功",
                type: "success",
              });
              this.getAllIndent();
            } else if(resp.data.code==400){
              this.$message({
                message: "进行中的订单不能删除",
                type: "warning",
              });
            }
          })
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    // 搜索人员
    search() {
      this.indentDTO.from=this.ArrayDate[0];
      this.indentDTO.to=this.ArrayDate[1];
      console.log(this.indentDTO)
      this.$axios
        .post(
          "http://localhost:8080/indent/get/" +this.pageIndex +"/" +this.pageSize,this.indentDTO)
        .then((resp) => {
          if(resp.data.code==200){
            this.pageBean = resp.data.data;
            this.$message({
            type: "success",
            message: "查询成功"
          })
          }else{
            this.$message({
            type: "error",
            message: "查询失败"
            })
          }
        });
    },
    // 搜索重置
    reset() {
      this.indentDTO={sendWay:{shId: null},from:"",to:"",};
      this.$message({
        type: "success",
        message: "重置成功",
      });
    },
    //点击一行也加入多选框
    handleRowClick(row, column, event) {
      // 点击某一行时，切换该行的勾选状态，并更新selectedRows数组
      /* this.$refs.multipleTable.toggleRowSelection(row);
      let index = this.selectedRows.indexOf(row);
      if (index > -1) {
        this.selectedRows.splice(index, 1);
      } else {
        this.selectedRows.push(row);
      } */
    },
    // 用于保存选中的行
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //种类标签相关方法
    filterKind(value, row, column) {
        return row.kind.zlName === value;
    },
    //支付方式标签相关方法
    filterPayway(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },
    //交易状态标签相关方法
    filterStatus(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },
  },
};
</script>
  
  <style>
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
  background: #f2ca00;
  margin: 3px;
}
.css-button-rounded--yellow:hover {
  background: #f7dc59;
}
/* 蓝色按钮 */
.css-button-rounded--blue {
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
  background: #07c1e2;
  margin: 3px;
}
.css-button-rounded--blue:hover {
  background: #17dee9;
}
/* 详情按钮 */
.css-button-fully-rounded--yellow {
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
  border: 2px solid #f7dc59;
  background: #f7dc59;
}
.css-button-fully-rounded--yellow:hover {
  background: #fff;
  color: #ffd819
}
/* 导出按钮 */
.css-button-rounded--export {
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
  background: #3a86ff;
  margin: 3px;
}
.css-button-rounded--export:hover {
  background: #79b3ff;
}
/* 导入按钮 */
.css-button-rounded--import {
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
  background: #3d348b;
  margin: 3px;
}
.css-button-rounded--import:hover {
  background: #1b569e;
}
/* 物流按钮 */
.css-button-arrow--sky {
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
  background-color: #3a86ff
}
.css-button-arrow--sky:hover {
  border-radius: 5px;
  padding-right: 24px;
  padding-left:8px;
}
.css-button-arrow--sky:hover:after {
  opacity: 1;
  right: 10px;
}
.css-button-arrow--sky:after {
  content: "\00BB";
  position: absolute;
  opacity: 0;
  font-size: 20px;
  line-height: 40px;
  top: 0;
  right: -20px;
  transition: 0.4s;
}
/* 确认按钮 */
.css-button-arrow--green {
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
  background-color: #57cc99
}
.css-button-arrow--green:hover {
  border-radius: 5px;
  padding-right: 24px;
  padding-left:8px;
}
.css-button-arrow--green:hover:after {
  opacity: 1;
  right: 10px;
}
.css-button-arrow--green:after {
  content: "\00BB";
  position: absolute;
  opacity: 0;
  font-size: 20px;
  line-height: 40px;
  top: 0;
  right: -20px;
  transition: 0.4s;
}
/* 付款按钮 */
.css-button-arrow--sky2 {
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
  background-color: #07cce264
}
.css-button-arrow--sky2:hover {
  border-radius: 5px;
  padding-right: 24px;
  padding-left:8px;
}
.css-button-arrow--sky2:hover:after {
  opacity: 1;
  right: 10px;
}
.css-button-arrow--sky2:after {
  content: "\00BB";
  position: absolute;
  opacity: 0;
  font-size: 20px;
  line-height: 40px;
  top: 0;
  right: -20px;
  transition: 0.4s;
}

/* 售后按钮 */
.css-button-arrow--gre {
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
  background-color: #61ef1a79
}
.css-button-arrow--gre:hover {
  border-radius: 5px;
  padding-right: 24px;
  padding-left:8px;
}
.css-button-arrow--gre:hover:after {
  opacity: 1;
  right: 10px;
}
.css-button-arrow--gre:after {
  content: "\00BB";
  position: absolute;
  opacity: 0;
  font-size: 20px;
  line-height: 40px;
  top: 0;
  right: -20px;
  transition: 0.4s;
}
/* 删除按钮 */
.css-button-arrow--red {
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
  background-color: #f34646
}
.css-button-arrow--red:hover {
  border-radius: 5px;
  padding-right: 24px;
  padding-left:8px;
}
.css-button-arrow--red:hover:after {
  opacity: 1;
  right: 10px;
}
.css-button-arrow--red:after {
  content: "\00BB";
  position: absolute;
  opacity: 0;
  font-size: 20px;
  line-height: 40px;
  top: 0;
  right: -20px;
  transition: 0.4s;
}
</style>