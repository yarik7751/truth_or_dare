package by.yarik.truth_or_dare.view.start

import android.os.Bundle
import android.util.Log
import by.yarik.truth_or_dare.base.basepresenter.BasePresenter
import by.yarik.truth_or_dare.base.basepresenter.IBasePresenter
import by.yarik.truth_or_dare.core.IResourceManager
import by.yarik.truth_or_dare.firebase.pojo.Level
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StartPresenter(view : IStartView, resourceManager: IResourceManager) : BasePresenter<IStartView>(view, resourceManager), IStartPresenter {

    companion object {
        val LEVELS = "levels";
    }

    override fun onCreateView() {
    }

    override fun onViewCreated(bundle: Bundle?) {
        getLevels();
    }

    private fun getLevels() {
        var database = FirebaseDatabase.getInstance();
        var mainReference = database.reference;
        var levelReference = mainReference.child(LEVELS);

        levelReference.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                Log.d("FirebaseDatabase", "onDataChange");
                for(post in dataSnapshot.children) {
                    var level: Level = post.getValue(Level::class.java)!!
                    Log.d("FirebaseDatabase", "forEach");
                }
                Log.d("FirebaseDatabase", "model");
            }

            override fun onCancelled(databaseError: DatabaseError) {
                view.showError(databaseError.message)
            }
        })
    }
}