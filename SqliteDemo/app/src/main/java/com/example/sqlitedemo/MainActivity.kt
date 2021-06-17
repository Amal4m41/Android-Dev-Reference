package com.example.sqlitedemo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlitedemo.Adapters.ItemAdapter
import com.example.sqlitedemo.Models.EmpModelClass
import com.example.sqlitedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)   //making the layour visible for us to access.
        setContentView(binding.root)

        setupListofDataIntoRecyclerView()  //update the recycler view

        binding.AddRecordButton.setOnClickListener {
            addRecord()
        }
    }


    /**
     * Function is used show the list of data in the database.
     */
    private fun setupListofDataIntoRecyclerView() {

        val employeeList:ArrayList<EmpModelClass> = getItemsList()
        if (employeeList.size > 0) {

            binding.recordsListRecyclerView.visibility = View.VISIBLE
            binding.NoRecordsAvailableText.visibility = View.GONE

            //Attaching the adapter to the recycler view
            binding.recordsListRecyclerView.layoutManager = LinearLayoutManager(this)
            // Adapter class is initialized and list is passed in the param.

            val itemAdapter = ItemAdapter(this, employeeList)
            // adapter instance is set to the recyclerview to inflate the items.
            binding.recordsListRecyclerView.adapter = itemAdapter
        } else {
            //Show empty records screen
            binding.recordsListRecyclerView.visibility = View.GONE
            binding.NoRecordsAvailableText.visibility = View.VISIBLE
        }
    }

    /**
     * Function is used to get the Items List which from the database table.
     */
    private fun getItemsList(): ArrayList<EmpModelClass> {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val empList: ArrayList<EmpModelClass> = databaseHandler.viewEmployee()

        return empList
    }

    //method for adding records to the database
    private fun addRecord() {
        val name = binding.etName.text.toString()
        val email = binding.etEmail.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (!name.isEmpty() && !email.isEmpty()) {
            //id is autoincrement for the database, therefore just putting dummy value here
            val status = databaseHandler.addEmployee(EmpModelClass(0, name, email))
            if (status > -1) {
                Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                binding.etName.text.clear()
                binding.etEmail.text.clear()

                setupListofDataIntoRecyclerView()
            }
            else{
                Log.e("TAG", "addRecord: Not successful")
            }
        }
        else {
            Toast.makeText(this, "Name or Email cannot be blank", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Method is used to show the Custom Dialog.
     */
    fun updateRecordDialog(empModelClass: EmpModelClass) {
        //Only once this update dialog is shown the user will be able to press the Update and Cancel button, therefore the
        //listeners for those button will be defined here.

        val updateCustomDialog = Dialog(this,R.style.Theme_AppCompat_Light_Dialog)
        updateCustomDialog.setCancelable(false)
        /*Set the screen content from a layout resource.
         The resource will be inflated, adding all top-level views to the screen.*/
        updateCustomDialog.setContentView(R.layout.dialog_update_record)

        val nameField=updateCustomDialog.findViewById<TextView>(R.id.etNameEdited)
        val emailField=updateCustomDialog.findViewById<TextView>(R.id.etEmailEdited)
        //Showing the current value to edit/update
        nameField.text=empModelClass.name
        emailField.text=empModelClass.email

        val updateButton=updateCustomDialog.findViewById<TextView>(R.id.updateButton)
        val cancelButton=updateCustomDialog.findViewById<TextView>(R.id.cancelButton)


        updateButton.setOnClickListener {
            //Get the values to be updated
            val name = nameField.text.toString()
            val email = emailField.text.toString()

            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            if (!name.isEmpty() && !email.isEmpty()) {
                //passing the value with the current id to be updated with the edited values
                val status = databaseHandler.updateEmployee(EmpModelClass(empModelClass.id, name, email))
                if (status > -1) {
                    Toast.makeText(applicationContext, "Record Updated.", Toast.LENGTH_LONG).show()

                    setupListofDataIntoRecyclerView()    //update the recycler view

                    updateCustomDialog.dismiss() // Dialog will be dismissed
                }
            }
            else {
                Toast.makeText(this, "Name or Email cannot be blank", Toast.LENGTH_LONG).show()
            }

        }

        cancelButton.setOnClickListener {
            updateCustomDialog.dismiss()
        }
        //match the parent width and wrap content for height.
        updateCustomDialog.getWindow()?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        //Start the dialog and display it on screen.
        updateCustomDialog.show()
    }

    /**
     * Method is used to show the Alert Dialog.
     */
    fun deleteRecordAlertDialog(empModelClass: EmpModelClass) {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Delete Record")
        //set message for alert dialog
        builder.setMessage("Are you sure you wants to delete ${empModelClass.name} record?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Yes") { dialogInterface, which ->

            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseHandler = DatabaseHandler(this)
            //calling the deleteEmployee method of DatabaseHandler class to delete record
            //only the id/primary key is needed for deleting the record
            val status = databaseHandler.deleteEmployee(EmpModelClass(empModelClass.id, "", ""))
            if (status > -1) {
                Toast.makeText(this, "Record deleted successfully.", Toast.LENGTH_LONG).show()

                setupListofDataIntoRecyclerView()
            }

            dialogInterface.dismiss() // Dialog will be dismissed
        }
        //performing negative action
        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogInterface.dismiss() // Dialog will be dismissed
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false) // Will not allow user to cancel after clicking on remaining screen area.
        alertDialog.show()  // show the dialog to UI
    }
}