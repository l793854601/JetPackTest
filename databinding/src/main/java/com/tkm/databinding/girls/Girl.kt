package com.tkm.databinding.girls

data class Girl(
    val avatar: String?,
    val name: String?,
) {
    companion object {
        fun createDataSource(): List<Girl> {
            val list = mutableListOf<Girl>()
            list.add(Girl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201901%2F30%2F20190130124544_GRHxx.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663939653&t=76288cda72c32221f28221f7ec095f5a", "赖美云"))
            list.add(Girl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fx0.ifengimg.com%2Fres%2F2022%2F4D703E9DEB39AEB41504B7E6FB6E1790A9454671_size179_w1280_h760.jpeg&refer=http%3A%2F%2Fx0.ifengimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663939671&t=41c23e8a415f37c55d8a6c96a31a8028", "王心凌"))
            list.add(Girl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhlpic.gog.cn%2Ftest%2F20170507%2F79%2F3483894677738398219.jpg&refer=http%3A%2F%2Fhlpic.gog.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663939445&t=b3c36eca0edc06cac90808b770d07251", "刘惜君"))
            list.add(Girl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fgss0.baidu.com%2F-4o3dSag_xI4khGko9WTAnF6hhy%2Fzhidao%2Fpic%2Fitem%2Fa8014c086e061d957e73ae377bf40ad162d9ca8c.jpg&refer=http%3A%2F%2Fgss0.baidu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663939688&t=49dd95461120a9639ae6a09aadbbaaff", "郭静"))
            list.add(Girl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbkimg.cdn.bcebos.com%2Fpic%2F7af40ad162d9f2d3572c35b940bd9d13632762d06611&refer=http%3A%2F%2Fbkimg.cdn.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1663939713&t=cae807a2150c4cf0d965568ce6d3985e", "黄丽玲"))
            list.add(Girl("", "ymsb"))

            return list.toList()
        }
    }
}
