import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.scss'
})
export class FooterComponent {

  private goToLink(url: string) {
    window.open(url, "_blank");
  }

  openGithubMk1808() {
    this.goToLink("https://github.com/mk1808");
  }

  openGithubMarqos12() {
    this.goToLink("https://github.com/marqos12");
  }
}
