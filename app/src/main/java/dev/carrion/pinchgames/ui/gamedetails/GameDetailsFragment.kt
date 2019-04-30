package dev.carrion.pinchgames.ui.gamedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dev.carrion.pinchgames.R
import dev.carrion.pinchgames.domain.DomainEntity
import dev.carrion.pinchgames.domain.toBigCoverUrl
import kotlinx.android.synthetic.main.fragment_game_details.*
import kotlinx.android.synthetic.main.item_recycler_game.imgCoverDetails
import org.koin.android.ext.android.setProperty
import org.koin.androidx.viewmodel.ext.android.getViewModel

class GameDetailsFragment : Fragment() {

    private val args: GameDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setProperty("id", args.id)

        val viewModel = getViewModel<GameDetailsViewModel>()

        viewModel.gameDetail.observe(this, Observer {
            setGameDetails(it, view)
        })
    }

    private fun setGameDetails(game: DomainEntity.Game, view: View){

        game.cover?.let {
            Glide.with(view).load(it.toBigCoverUrl()).into(imgCoverDetails)
        }
        txtNameDetails.text = game.name
        game.summary?.let {
            txtSumary.text = it
        }
        txtUrl.text = game.url
    }
}