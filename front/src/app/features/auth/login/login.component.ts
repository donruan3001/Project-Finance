import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private fb:FormBuilder,private auth: AuthService,private router: Router) { }
  
  form = this.fb.nonNullable.group({
    email: ['',[Validators.required, Validators.email]],
    password: ['', [Validators.required]]
  });
  
  loading = false;
  errorMessage: string | undefined;

  ngOnInit(): void {
  }

  submit() {
    if (this.form.invalid) return;
    this.loading = true;
    this.auth.login(this.form.getRawValue()).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/accounts']);
      },
      error: (err) => {
        this.loading = false;
        this.errorMessage = 'E-mail ou senha inválidos';
      }
    });
}
}