package com.codemon.fastfoodandextremedelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var selectedImageButton: ImageView? = null
    private val imageButtons = mutableListOf<ImageView>()
    private val cardTexts = mutableListOf<CardText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        initListeners()
    }

    private fun initUi(){
        imageButtons.add(firstStepIB)
        imageButtons.add(secondStepIB)
        imageButtons.add(thirdStepIB)

        cardTexts.add(CardText(index = 0, title = getString(R.string.card_title_1), text = getString(R.string.card_content_1)))

        cardTexts.add(CardText(index = 1, title = getString(R.string.card_title_2), text = getString(R.string.card_content_2)))

        cardTexts.add(CardText(index = 2, title = getString(R.string.card_title_3), text = getString(R.string.card_content_3)))

        selectButton(imageButtons[0])
    }

    private fun initListeners(){
        nextBtn.setOnClickListener {
            handleNextButton()
        }
    }

    private fun handleNextButton(){
        if(selectedImageButton == null)
            selectButton(imageButtons[0])

        var tag = (selectedImageButton!!.tag as String).toInt()

        tag++
        if(tag >= imageButtons.size)
            tag = 0

        selectButton(imageButtons[tag])
    }

    private fun selectButton(imageButton: ImageView){

        if(selectedImageButton != null){
            //Deselect all other buttons
            for (_imageButton in imageButtons){
                if(_imageButton != imageButton)
                    _imageButton.clearFocus()
            }
        }
        var tag = (imageButton.tag as String).toInt()

        cardTitleTV.text = cardTexts[tag].title
        cardContentTV.text = cardTexts[tag].text

        //Only set button
        imageButton.isFocusableInTouchMode = true
        imageButton.requestFocus()
        selectedImageButton = imageButton
    }
}