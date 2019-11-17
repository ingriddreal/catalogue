package com.ingridr.catalogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.Toolbar
import com.ingridr.catalogue.ui.main.CatalogueListFragment

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val toolBar = findViewById<Toolbar>(R.id.my_toolbar)
        toolBar.title = getString(R.string.app_name_toolbar)
        setSupportActionBar(toolBar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
        startCatalogue()
    }
    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun startCatalogue() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContainer, CatalogueListFragment.newInstance())
        transaction.addToBackStack(null)
        transaction.commit()
    }
//    fun show(product: Product) {
//
//        val productFragment = ProductFragment.forProduct(product.getId())
//
//        supportFragmentManager
//            .beginTransaction()
//            .addToBackStack("product")
//            .replace(
//                R.id.fragment_container,
//                productFragment, null
//            ).commit()
//    }


}
