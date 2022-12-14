package com.experiments.android.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.experiments.android.data.source.local.model.TxnEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TxnDaoTest {

    // Executes each task synchronously using Architecture Components
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: TxnDatabase

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TxnDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun insertTransactionAndGetById() = runBlockingTest {
        // GIVEN - Insert a transaction
        val txn = TxnEntity(transactionId = 1, amount = 12.34, description = "description")
        db.txnDao().insert(txn)

        // WHEN - Get the transaction by id from the database
        val loaded = db.txnDao().getTransactionWithId(txn.transactionId)

        // THEN - The loaded data contains the expected values
        assertIs<TxnEntity>(loaded)
        assertNotNull(loaded)
        assertEquals(txn.transactionId, loaded.transactionId)
        assertEquals(txn.amount, loaded.amount)
        assertEquals(txn.description, loaded.description)
    }

    @Test
    fun updateTransactionAndGetById() = runBlockingTest {
        // GIVEN - Insert a transaction into the DAO
        val txn = TxnEntity(transactionId = 1, amount = 12.34, description = "description")
        db.txnDao().insert(txn)

        // WHEN - Update transaction by creating new transaction with same ID but
        // different attributes, and get by id
        val txnNew = txn.copy(description = "updated")
        db.txnDao().update(txnNew)
        val loaded = db.txnDao().getTransactionWithId(txn.transactionId)

        // THEN - The loaded data contains the updated values
        assertIs<TxnEntity>(loaded)
        assertNotNull(loaded)
        assertEquals(txn.transactionId, loaded.transactionId)
        assertEquals(txn.amount, loaded.amount)
        assertEquals(txnNew.description, loaded.description)

    }
}
