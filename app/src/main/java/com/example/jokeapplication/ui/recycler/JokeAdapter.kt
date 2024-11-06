import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapplication.R
import com.example.jokeapplication.data.Joke
import com.example.jokeapplication.databinding.ItemJokeBinding
import com.example.jokeapplication.ui.joke_details.JokeDetailsActivity

class JokeAdapter(private val jokes: List<Joke>) :
    RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    inner class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.categoryTextView)
        private val questionTextView: TextView = itemView.findViewById(R.id.questionTextView)
        private val answerTextView: TextView = itemView.findViewById(R.id.answerTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(joke: Joke) {
            categoryTextView.text = joke.category
            questionTextView.text = joke.question
            answerTextView.text = joke.answer
            joke.image?.let {
                imageView.setImageResource(it)
            } ?: run {
                imageView.visibility = View.GONE
            }
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, JokeDetailsActivity::class.java).apply {
                    putExtra("EXTRA_CATEGORY", joke.category)
                    putExtra("EXTRA_QUESTION", joke.question)
                    putExtra("EXTRA_ANSWER", joke.answer)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_joke, parent, false)
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    override fun getItemCount(): Int = jokes.size
}