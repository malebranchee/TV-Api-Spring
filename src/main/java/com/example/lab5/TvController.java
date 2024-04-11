package com.example.lab5;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RequiredArgsConstructor
@Controller
public class TvController {
    @NonNull
    private final TvService tvService;


    @GetMapping("/tv")
    public String getAllTv(@ModelAttribute TvEntity tvEntity, Model model){
        model.addAttribute("tvEntities", tvService.getAllTv());
        return "tv";
    }



    @PostMapping("/add")
    public String addTv(@ModelAttribute TvEntity tvEntity, Model model){

        model.addAttribute("tvEntity", tvEntity);
        tvService.addTv(tvEntity);
        return "addResult";
    }

    @GetMapping("/add")
    public String addTvResult(Model model){
        model.addAttribute("tvEntity", new TvEntity());
        return "add";
    }

    @PostMapping("/delete")
    public String deleteTv(@ModelAttribute TvEntity tvEntity, Model model){
         model.addAttribute("tvId", tvEntity);
         if (tvService.existsTv(tvEntity.getId())) {
             tvService.deleteTv(tvEntity.getId());
             return "deleteResult";
         }
             return "deleteNoResult";
    }

    @GetMapping("/delete")
    public String deleteTvResult(Model model){
        model.addAttribute("tvId", new TvEntity());
        return "delete";
    }

    @GetMapping("/edit")
    public String updateTv(Model model){

        model.addAttribute("tvEntity", new TvEntity());

        return "checkConditionEdit";
    }

    @PostMapping("/edit")
    public String editTv(@ModelAttribute TvEntity tvEntity, Model model){
        if (tvService.existsTv(tvEntity.getId())) {
            model.addAttribute("tvEntity", tvService.loadUserById(tvEntity.getId()));
            return "editExist";
        }
        return "add";
    }
    @PostMapping("/edit/successful")
    public String editTvSuccess(@ModelAttribute TvEntity tvEntity, Model model){
        model.addAttribute("tvEntity", tvService.saveTv(tvEntity));
        return "editSuccessful";
    }

    @GetMapping("/search")
    public String searchTvResult(Model model){
        model.addAttribute("tvEntity", new TvEntity());
        return "search";
    }

    @PostMapping("/search")
    public String searchTv(@ModelAttribute TvEntity tvEntity, Model model){
        if (tvService.existsTv(tvEntity.getId())) {
            model.addAttribute("tvEntity", tvService.loadUserById(tvEntity.getId()));
            return "searchResult";
        }

        return "searchNoResult";
    }

    @GetMapping("/search/by/price")
    public String searchTvByLowPrice(Model model){
        model.addAttribute("tvEntity", new TvEntity());
        return "searchByPrice";
    }

    @PostMapping("/search/by/price")
    public String searchTvByLowPrice(@ModelAttribute TvEntity tvEntity, Model model){
        if (tvService.searchByLowPrice(tvEntity.getPrice()).isEmpty()){
            return "searchByPriceNoResult";
        }
        model.addAttribute("tvEntity", tvService.searchByLowPrice(tvEntity.getPrice()));
        return "searchByPriceResult";
    }

}
