package com.john.home_impl.data.repository.impl

import com.john.domain.exceptions.DefaultHttpException
import com.john.domain.exceptions.DefaultSocketException
import com.john.home_impl.domain.entities.HomeRecipePreview
import com.john.home_impl.api.HomeApi
import com.john.home_impl.data.mappers.HomeRecipeReviewMapper
import com.john.home_impl.data.repository.HomeRepository
import java.net.SocketException
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException

class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeRepository {

    override suspend fun getReceipts(): Result<List<HomeRecipePreview>> {
        return try {
            val response = homeApi.getRecipes()
            Result.success(HomeRecipeReviewMapper.map(response.recipes))
        } catch (exception: SocketException) {
            Result.failure(
                DefaultSocketException(message = exception.message ?: exception.toString())
            )
        } catch (exception: HttpException) {
            Result.failure(
                DefaultHttpException(message = exception.message())
            )
        } catch (exception: Exception) {
            if (exception is CancellationException) throw exception
            Result.failure(exception)
        }
    }
}