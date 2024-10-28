import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jokeapplication.data.Joke
import com.example.jokeapplication.databinding.ItemJokeBinding

class JokeAdapter(private val jokes: List<Joke>) :
    RecyclerView.Adapter<JokeAdapter.JokeViewHolder>() {

    inner class JokeViewHolder(private val binding: ItemJokeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(joke: Joke) {
            binding.categoryTextView.text = joke.category
            binding.questionTextView.text = joke.question
            binding.answerTextView.text = joke.answer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemJokeBinding.inflate((inflater))
        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    override fun getItemCount(): Int = jokes.size
}