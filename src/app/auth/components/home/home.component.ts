import { Component, OnInit } from '@angular/core';
import { AuthService } from "../../services/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  authenticated: boolean = false;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
    this.authService.isUserLoggedIn().subscribe(isLoggedIn => {
      this.authenticated = isLoggedIn;
    });
  }
}
