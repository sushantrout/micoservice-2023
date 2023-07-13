import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'hotel-management-system';

  constructor(private http: HttpClient) {}
  ngOnInit(): void {
    this.http.get("http://localhost:8084/users").subscribe((res : any) => {
      console.log(res)
    });
  }
}
