package com.prilepskiy.shoppingBackend.controller

import com.prilepskiy.shoppingBackend.domain.model.DisheDTO
import com.prilepskiy.shoppingBackend.domain.service.DisheService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Dishe")
class DisheController(private val dusheService: DisheService) {
    @GetMapping
    fun getDihse() = dusheService.getDishe().map {
        DisheDTO(
            description = it.description,
            id = it.id,
            categoryName = it.categoryName,
            image_url = it.image_url,
            name = it.name,
            price = it.price,
            tegs = it.tegs,
            weight = it.weight
        )
    }

    @GetMapping("/byCategories")
    @ResponseBody
    fun getDisheByCategory(@RequestParam categoryName: String) = dusheService.getDisheByCategory(categoryName).map {
        DisheDTO(
            description = it.description,
            id = it.id,
            categoryName = it.categoryName,
            image_url = it.image_url,
            name = it.name,
            price = it.price,
            tegs = it.tegs,
            weight = it.weight
        )
    }
//    @ResponseBody
//    fun getDisheByCategory(@RequestParam id:String,@RequestParam categoryName:String)=dusheService.getDisheByCategory(id=id,categoryName=categoryName).map {
//        DisheDTO(
//                description = it.description,
//                id = it.id,
//                categoryName = it.categoryName,
//                image_url = it.image_url,
//                name = it.name,
//                price = it.price,
//                tegs=it.tegs,
//                weight = it.weight
//        )
//    }

}
