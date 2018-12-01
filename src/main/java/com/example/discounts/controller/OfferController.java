package com.example.discounts.controller;

import com.example.discounts.model.Offer;
import com.example.discounts.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OfferController {

    private OfferRepository offerRepository;

    public OfferController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }
    @GetMapping("/")
    public String home(Model model){
        List<Offer> offerList = offerRepository.findAll();
        model.addAttribute("offers",offerList);
        return "list";
    }
@GetMapping("/dodaj")
    public String showForm(Model model){
        model.addAttribute("offer", new Offer());
        return "addform";
}
@PostMapping("/zapisz")
public String saveOffer(Offer offer){
        offerRepository.save(offer);
        return "redirect:/";
}
@GetMapping("/oferta")
public String singleOffer(@RequestParam Long offerId, Model model){
        offerRepository.incrementViews(offerId);
        Offer offer = offerRepository.findById(offerId);
    model.addAttribute("offer", offer);
    return "singleoffer";
    }
}
