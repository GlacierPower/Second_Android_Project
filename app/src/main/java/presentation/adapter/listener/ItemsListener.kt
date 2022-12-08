package presentation.adapter.listener

interface ItemsListener {

    fun onClick()

    fun onItemSelected(name: String, date: String, imageView: Int)
}