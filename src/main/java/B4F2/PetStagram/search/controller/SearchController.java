package B4F2.PetStagram.search.controller;

import B4F2.PetStagram.member.util.JwtAuthenticationProvider;
import B4F2.PetStagram.search.domain.SearchParam;
import B4F2.PetStagram.search.service.SearchService;
import B4F2.PetStagram.member.entity.Member;
import B4F2.PetStagram.tag.entity.TagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class SearchController {

    private final SearchService searchService;
    private final JwtAuthenticationProvider provider;

    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@RequestParam String keyword) {
        var result = this.searchService.autocomplete(keyword);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/search")
    public String list(Model model, SearchParam parameter) {

        String type = parameter.getSearchType();
        String value = parameter.getSearchValue();

        if (type.equals("id")) {
            List<Member> context = searchService.searchByEmail(value);
            System.out.println(context);
            model.addAttribute("list", context);

        } else if (type.equals("tag")) {
            List<TagEntity> context = searchService.getByTagTitle(value);
            System.out.println(context);
            model.addAttribute("list", context);

        }

        return "search";
    }
    @GetMapping("/search/id")
    public ResponseEntity<List<Member>> searchByEmail(@RequestParam String email) {
        return ResponseEntity.ok(
                searchService.searchByEmail(email).stream()
                        .map(Member::SearchFrom).collect(Collectors.toList())
        );
    }
}
