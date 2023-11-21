package com.john.recipe_detail_impl.data.repository.impl

import com.john.recipe_detail_impl.api.RecipeDetailApi
import com.john.recipe_detail_impl.data.mappers.RecipeDetailMapper
import com.john.recipe_detail_impl.data.repository.RecipeDetailRepository
import com.john.recipe_detail_impl.domain.entities.RecipeDetail
import com.john.domain.exceptions.DefaultHttpException
import com.john.domain.exceptions.DefaultSocketException
import java.net.SocketException
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException

class RecipeDetailRepositoryImpl @Inject constructor(
    private val recipeDetailApi: RecipeDetailApi
) : RecipeDetailRepository {
    override suspend fun getRecipeDetail(id: Long): Result<RecipeDetail> {
        return try {
            val response = recipeDetailApi.getRecipeDetail()
            Result.success(RecipeDetailMapper.map(response))
        } catch (exception: SocketException) {
            Result.failure(
                DefaultSocketException(
                    message = exception.message ?: exception.toString()
                )
            )
        } catch (exception: HttpException) {
            Result.failure(
                DefaultHttpException(
                    message = exception.message()
                )
            )
        } catch (exception: Exception) {
            if (exception is CancellationException) throw exception
            Result.failure(exception)
        }
    }
}