package com.st11.epartyentry.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.st11.epartyentry.model.InviteeEntity
import kotlinx.coroutines.flow.Flow

//This interface defines the database operations.
@Dao
interface InviteeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvitee(invitee: InviteeEntity)

    @Update
    suspend fun updateInvitee(invitee: InviteeEntity)


    @Query("UPDATE invitee SET isCheckIn = :isCheckIn WHERE eventId = :eventId")
    suspend fun updateInviteeStatus(isCheckIn: Boolean, eventId: String): Int?

    @Query("UPDATE invitee SET fullName = :fullName, phone = :phone WHERE inviteId = :inviteId")
    suspend fun updateInviteeById(fullName: String, phone: String, inviteId: String): Int?

    @Query("DELETE FROM invitee WHERE inviteId = :inviteId")
    suspend fun deleteInviteeById(inviteId: String): Int?


    @Query("SELECT * FROM invitee WHERE eventId = :eventId ORDER BY timestamp DESC")
    fun getAllInvitee(eventId: String): Flow<List<InviteeEntity>>

    @Query("SELECT COUNT(*) FROM invitee WHERE eventId = :eventId")
    fun getAllTotalInvitee(eventId: String): Flow<Int?>

    @Query("SELECT * FROM invitee WHERE eventId = :eventId AND isCheckIn = 1 ORDER BY timestamp DESC")
    fun getAllInviteeCheckIn(eventId: String): Flow<List<InviteeEntity>>

//   @Query("SELECT * FROM invitee WHERE phone = :phone AND eventId = :eventId")
//   fun getInviteeByPhone(phone: String, eventId: String): Flow<InviteeEntity?>
//@Query("SELECT EXISTS(SELECT 1 FROM invitee WHERE phone = :phone AND eventId = :eventId)")
//suspend fun isInviteePresent(phone: String, eventId: String): Boolean

    @Query("SELECT * FROM invitee WHERE phone = :phone AND eventId = :eventId AND isCheckIn = 1 LIMIT 1")
    suspend fun getInviteeByPhoneAndEvent(phone: String, eventId: String): InviteeEntity?

    @Query("SELECT * FROM invitee WHERE phone = :phone AND eventId = :eventId LIMIT 1")
    suspend fun getInviteeByPhoneAndEventIfInvited(phone: String, eventId: String): InviteeEntity?


    @Query("SELECT * FROM invitee WHERE inviteId = :inviteId LIMIT 1")
   suspend fun getInviteeByInviteId(inviteId: String): InviteeEntity?
//    @Query("""
//    UPDATE invitee
//    SET isCheckIn = 1, checkInTime = :checkInTime , checkInDate = :checkInDate
//    WHERE phone = :phone AND eventId = :eventId
//""")
//    suspend fun updateCheckIn(phone: String, eventId: String, checkInTime: String, checkInDate: String)

    @Query("""
    UPDATE invitee
    SET isCheckIn = 1, 
        checkInTime = :checkInTime, 
        checkInDate = :checkInDate
    WHERE phone = :phone AND eventId = :eventId
""")
    suspend fun updateCheckIn(
        phone: String,
        eventId: String,
        checkInTime: String,
        checkInDate: String
    )



}