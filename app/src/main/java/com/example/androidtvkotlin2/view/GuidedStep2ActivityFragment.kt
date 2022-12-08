package com.example.androidtvkotlin2.view

import android.os.Bundle
import androidx.leanback.app.GuidedStepSupportFragment
import androidx.leanback.widget.GuidanceStylist
import androidx.leanback.widget.GuidedAction

class GuidedStep2ActivityFragment : GuidedStepSupportFragment() {

    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        return GuidanceStylist.Guidance("Sample Title", "description", "breadcrumbs 2", null)
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        super.onCreateActions(actions, savedInstanceState)

        val subActions = ArrayList<GuidedAction>()
        subActions.add(GuidedAction.Builder(requireContext())
            .title("Sub Action")
            .description("sub description")
            .build())
        val action6 = GuidedAction.Builder(requireContext())
            .title("Action")
            .description("Description")
            .focusable(true)
            .subActions(subActions)
            .build()

        val action7 = GuidedAction.Builder(requireContext())
            .title("Action")
            .description("Editable description")
            .descriptionEditable(true)
            .build()

        val action8 = GuidedAction.Builder(requireContext())
            .title("Editable title")
            .description("Description")
            .editable(true)
            .build()

        actions.add(action6)
        actions.add(action7)
        actions.add(action8)
    }

    override fun onGuidedActionClicked(action: GuidedAction?) {
        super.onGuidedActionClicked(action)

    }
}