package com.john.home.data.repository.impl

import com.john.domain.exceptions.DefaultHttpException
import com.john.domain.exceptions.DefaultSocketException
import com.john.home.api.HomeApi
import com.john.home.data.mappers.HomeRecipeReviewMapper
import com.john.home.data.repository.HomeRepository
import com.john.home.domain.HomeRecipePreview
import java.net.SocketException
import javax.inject.Inject
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
            Result.failure(exception)
        }
    }
}