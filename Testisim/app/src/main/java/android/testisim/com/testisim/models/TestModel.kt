package android.testisim.com.testisim.models

data class TestModel(val date: String, val isSuccess: Boolean)

data class TestModelList(val listOfTest: ArrayList<TestModel>)