package com.vn.controller.sanglv;

import com.vn.entities.MemberTransaction;
import com.vn.service.MemberService;
import com.vn.service.impl.CustomUserDetails;
import com.vn.utils.DateTimeUtil;
import com.vn.utils.MoneyUtil;
import com.vn.utils.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserControllerSangLV {
    @Autowired
    MemberService memberService;

    @GetMapping("/my_wallet")
    public String myWalletPage(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size,
                               @RequestParam(value = "date1", required = false, defaultValue = "") String date1,
                               @RequestParam(value = "date2", required = false, defaultValue = "") String date2) {
        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("fullName", detail.getMember().getFullName());
        model.addAttribute("wallet", MoneyUtil.genMoney(detail.getMember().getWallet()));

        model.addAttribute("date1", date1);
        model.addAttribute("date2", date2);

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize);
        Page<MemberTransaction> resultPage;
        if (date1.equals("")) {
            resultPage = memberService.findByMember(detail.getMember().getId(),pageable);
        } else {
            resultPage = memberService.findByMemberAndDate(
                    detail.getMember().getId(),
                    DateTimeUtil.getDateTime(date1 + " 00:00"),
                    DateTimeUtil.getDateTime(date2 + " 23:59"),
                    pageable);
        }
        int totalPages = resultPage.getTotalPages();
        model.addAttribute("resultPage", resultPage);
        model.addAttribute("totalPages", totalPages);
        if (totalPages > 0) {
            model.addAttribute("pageList", PagingUtil.genPageList(totalPages, currentPage));
        }

        return "wallet";
    }
}
