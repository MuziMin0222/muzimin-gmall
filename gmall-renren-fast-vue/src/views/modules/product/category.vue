<template>
  <div>
    <el-dialog
      v-bind:title='title'
      :visible.sync='dialogVisible'
      width='30%'
      :close-on-click-modal='false'>
      <el-form :model='category'>
        <el-form-item label='分类名称'>
          <el-input v-model='category.name' autocomplete='off'></el-input>
        </el-form-item>
        <el-form-item label='图标'>
          <el-input v-model='category.icon' autocomplete='off'></el-input>
        </el-form-item>
        <el-form-item label='计量单位'>
          <el-input v-model='category.productUnit' autocomplete='off'></el-input>
        </el-form-item>
      </el-form>
      <span slot='footer' class='dialog-footer'>
    <el-button @click='dialogVisible = false'>取 消</el-button>
    <el-button type='primary' @click='submitData'>确 定</el-button>
      </span>
    </el-dialog>

    <el-tree :data='menu'
             :props='defaultProps'
             @node-click='handleNodeClick'
             show-checkbox
             node-key='catId'
             draggable
             :allow-drop='allowDrop'
             :expand-on-click-node='false'
             :default-expanded-keys='expandedKey'>
     <span class='custom-tree-node' slot-scope='{ node, data }'>
        <span>{{ node.label }}</span>
        <span>
          <el-button v-if='node.level <= 2'
                     type='text'
                     size='mini'
                     @click='() => append(data)'>
            Append
          </el-button>
          <el-button v-if='node.childNodes.length === 0'
                     type='text'
                     size='mini'
                     @click='() => remove(node, data)'>
            Delete
          </el-button>
          <el-button type='text'
                     size='mini'
                     @click='() => edit(data)'>
            Edit
          </el-button>
        </span>
      </span>
    </el-tree>
  </div>
</template>

<script>
export default {
  data() {
    return {
      maxLevel: 0,
      title: null,
      // append,edit
      dialogType: '',
      category: {
        name: '',
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        catId: null,
        icon: '',
        productUnit: ''
      },
      dialogVisible: false,
      menu: [],
      expandedKey: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    };
  },
  methods: {
    allowDrop(draggingNode, dropNode, type) {
      // 被拖动的当前节点以及所在的父节点总层数不能大于3
      // 被拖动节点的总层数, 当前拖拽的父节点所在的深度不等于3即可
      this.countNodeLevel(draggingNode.data)
      let deep = this.maxLevel - draggingNode.data.catLevel + 1
      console.log('当前深度', deep)

      if (type === 'inner') {
        return (deep + dropNode.level) <= 3
      } else {
        return (deep + dropNode.parent.level) <= 3
      }
    },
    countNodeLevel(node) {
      // 找出所有的子节点，求出最大深度
      if (node.children != null && node.children.length > 0) {
        for (let i = 0; i < node.children.length; i++) {
          if (node.children[i].catLevel > this.maxLevel) {
            this.maxLevel = node.children[i].catLevel
          }
          this.countNodeLevel(node.children[i])
        }
      }
    },
    handleNodeClick(data) {
      console.log(data);
    },
    getMenus() {
      this.$http({
        url: this.$http.adornUrl('/category/list/tree'),
        method: 'get'
      }).then(({data}) => {
        console.log('成功获取到数据。。。', data.data)
        this.menu = data.data
      })
    },
    submitData() {
      if (this.dialogType === 'append') {
        this.addCategory()
      }
      if (this.dialogType === 'edit') {
        this.updateCategory()
      }
    },
    addCategory() {
      console.log('提交的三级分类数据：', this.category)
      this.$http({
        url: this.$http.adornUrl('/category/save'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
      }).then(({data}) => {
        this.$message({
          message: '菜单添加成功',
          type: 'success'
        });
        this.dialogVisible = false
        this.getMenus()
        this.expandedKey = [this.category.parentCid];
      })
    },
    append(data) {
      console.log('append', data)
      this.dialogType = 'append'
      this.title = '添加分类'
      this.dialogVisible = true
      this.category.parentCid = data.catId
      this.category.catLevel = data.catLevel * 1 + 1
      this.category.name = ''
      this.category.parentCid = 0
      this.category.catLevel = 0
      this.category.showStatus = 1
      this.category.sort = 0
      this.category.catId = null
      this.category.icon = ''
      this.category.productUnit = ''
    },
    edit(data) {
      console.log('edit', data)
      this.dialogType = 'edit'
      this.title = '修改分类'
      this.dialogVisible = true

      // 发送请求获取当前节点最新的数据
      this.$http({
        url: this.$http.adornUrl(`/category/info/${data.catId}`),
        method: 'get'
      }).then(({data}) => {
        // 请求成功之后的数据
        console.log('要回显的数据', data)
        this.category.name = data.data.name
        this.category.catId = data.data.catId
        this.category.icon = data.data.icon
        this.category.productUnit = data.data.productUnit
        this.category.parentCid = data.data.parentCid
      })
    },
    updateCategory() {
      var {name, catId, icon, productUnit} = this.category
      this.$http({
        url: this.$http.adornUrl('/category/update'),
        method: 'post',
        data: this.$http.adornData({name, catId, icon, productUnit}, false)
      }).then(({data}) => {
        console.log(`更新数据${this.category.catId}成功`)
        // 关闭对话框
        this.dialogVisible = false
        // 刷新菜单
        this.getMenus()
        // 设置默认展开的菜单
        this.expandedKey = [this.category.parentCid];
      })
    },
    remove(node, data) {
      var ids = [data.catId]

      this.$confirm(`是否删除【${data.name}】菜单?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/category/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          console.log(`删除数据${ids}成功`)
          this.$message({
            message: '菜单删除成功',
            type: 'success'
          });
          this.getMenus();
          this.expandedKey = [node.parent.data.catId];
        })
      }).catch(() => {
      })
    }
  },
  created() {
    this.getMenus()
  }
}
</script>

<style>

</style>
