package kitchenpos.ui.api;

import kitchenpos.application.MenuService;
import kitchenpos.domain.Menu;
import kitchenpos.ui.dto.menu.MenuRequest;
import kitchenpos.ui.dto.menu.MenuResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class MenuRestController {
    private final MenuService menuService;

    public MenuRestController(final MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/api/menus")
    public ResponseEntity<Menu> create(@RequestBody final Menu menu) {
        final Menu created = menuService.create(menu);
        final URI uri = URI.create("/api/menus/" + created.getId());
        return ResponseEntity.created(uri)
                .body(created)
                ;
    }

    @GetMapping("/api/menus")
    public ResponseEntity<List<Menu>> list() {
        return ResponseEntity.ok()
                .body(menuService.list())
                ;
    }

    @PostMapping("/api/menus2")
    public ResponseEntity<MenuResponse> create2(@RequestBody final MenuRequest menuRequest) {
        final Menu created = menuService.create(menuRequest.toMenu());
        final URI uri = URI.create("/api/menus2/" + created.getId());
        return ResponseEntity.created(uri)
                .body(MenuResponse.of(created))
                ;
    }

    @GetMapping("/api/menus2")
    public ResponseEntity<List<MenuResponse>> list2() {
        return ResponseEntity.ok()
                .body(MenuResponse.ofList(menuService.list()))
                ;
    }
}
