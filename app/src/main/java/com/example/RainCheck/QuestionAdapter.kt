package com.example.raincheck

/**
 *
 */
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 *
 */
class QuestionAdapter(private val mList: List<Question>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    /**
     *
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context) .inflate(R.layout.question, parent, false)
        return ViewHolder(view)
    }

    /**
     * Binds the list items to a view.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val Questions = mList[position]

        holder.question.text = Questions.question
        holder.answer.text = Questions.answer
    }

    /**
     * Return the number of the items in the list.
     */
    override fun getItemCount(): Int { return mList.size }

    /**
     * Holds the views.
     */
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val question: TextView = itemView.findViewById(R.id.question_textView)
        val answer: TextView = itemView.findViewById(R.id.answers_textView)
    }
}