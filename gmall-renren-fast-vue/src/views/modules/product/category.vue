<template>
  <el-tree :data="menu" :props="defaultProps" @node-click="handleNodeClick"></el-tree>
</template>

<script>
export default {
  data () {
    return {
      menu: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    };
  },
  methods: {
    handleNodeClick(data) {
      console.log(data);
    },
    getMenus () {
      this.$http({
        url: this.$http.adornUrl('/category/list/tree'),
        method: 'get'
      }).then(({data}) => {
        console.log('成功获取到数据。。。', data.data)
        this.menu = data.data
      })
    }
  },
  created () {
    this.getMenus()
  }
}
</script>

<style>

</style>
