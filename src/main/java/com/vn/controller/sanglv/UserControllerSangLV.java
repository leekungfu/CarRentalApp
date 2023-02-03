package com.vn.controller.sanglv;

import com.vn.dto.WalletResponseDTO;
import com.vn.entities.Member;
import com.vn.entities.MemberTransaction;
import com.vn.service.MemberService;
import com.vn.service.MemberTransactionService;
import com.vn.service.impl.CustomUserDetails;
import com.vn.utils.DateTimeUtil;
import com.vn.utils.MoneyUtil;
import com.vn.utils.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class UserControllerSangLV {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberTransactionService memberTransactionService;

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

        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("date").descending());
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

        return "account/wallet";
    }

    @PostMapping("/top_up")
    @ResponseBody
    public ResponseEntity<?> topUp(@RequestBody Long amount) {
        WalletResponseDTO walletResponseDTO = new WalletResponseDTO();

        CustomUserDetails detail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberService.findById(detail.getMember().getId());
        if (member.getWallet() == null)
            member.setWallet(0.0);
        member.setWallet(member.getWallet() + amount);


        MemberTransaction memberTransaction = new MemberTransaction();
        memberTransaction.setAmount(amount + 0.0);
        memberTransaction.setDate(LocalDateTime.now());
        if (amount >0)
            memberTransaction.setType("TOP UP");
        else
            memberTransaction.setType("WITHDRAW");
        memberTransaction.setMember(member);
        member.getMemberTransactions().add(memberTransaction);

        memberService.updateWallet(member);
        memberTransactionService.save(memberTransaction);

        Pageable pageable = PageRequest.of(0, 5, Sort.by("date").descending());
        Page<MemberTransaction> resultPage = memberService.findByMember(detail.getMember().getId(), pageable);

        walletResponseDTO.setTran(resultPage.getContent());
        walletResponseDTO.setMessage("You have just top up " + amount);
        walletResponseDTO.setBalance(MoneyUtil.genMoney(member.getWallet()));

        return ResponseEntity.ok(walletResponseDTO);
    }
}
