package com.example.androidtvkotlin2.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.GuidanceStylist
import androidx.leanback.widget.GuidedAction
import com.example.androidtvkotlin2.R

class GuidedStepActivityFragment : GuidedStepSupportFragment() {

//    // For custom Action UI (right side)
//    override fun onCreateActionsStylist(): GuidedActionsStylist {
//        return object : GuidedActionsStylist() {
//            override fun onProvideItemLayoutId(): Int {
//                return super.onProvideItemLayoutId()
//            }
//
//            override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
//                return super.onCreateViewHolder(parent)
//            }
//        }
//    }
//
//    // For custom Guidance UI (left side)
//    override fun onCreateGuidanceStylist(): GuidanceStylist {
//        return object : GuidanceStylist() {
//            override fun onProvideLayoutId(): Int {
//                return super.onProvideLayoutId()
//            }
//
//            override fun onCreateView(
//                inflater: LayoutInflater?,
//                container: ViewGroup?,
//                guidance: Guidance?
//            ): View {
//                return super.onCreateView(inflater, container, guidance)
//            }
//        }
//    }

    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        return GuidanceStylist.Guidance("Sample Title", "description", "breadcrumbs 1", null)
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        super.onCreateActions(actions, savedInstanceState)
        
        val action1 = GuidedAction.Builder(requireContext())
            .title("Action")
            .description("Description")
            .focusable(true)
            .build()
        
        val action2 = GuidedAction.Builder(requireContext())
            .title("Action 2")
            .description("Description 2")
            .focusable(true)
            .hasNext(true)
            .build()

        val action3 = GuidedAction.Builder(requireContext())
            .title("Action 3")
            .description("Description 3")
            .focusable(true)
            .icon(R.drawable.ic_launcher_foreground)
            .build()

        actions.add(action1)
        actions.add(action2)
        actions.add(action3)
    }

    override fun onGuidedActionClicked(action: GuidedAction?) {
        super.onGuidedActionClicked(action)
        add(parentFragmentManager, GuidedStep2ActivityFragment())
    }

}


