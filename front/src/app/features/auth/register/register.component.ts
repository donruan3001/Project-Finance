import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
form = this.fb.nonNullable.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
  });
ngOnInit(): void {
    

}
  message = '';
  loading = false;

  constructor(private fb: FormBuilder, private auth: AuthService, private router: Router) {}

  submit() {
    if (this.form.invalid) return;
    this.loading = true;
    this.auth.register(this.form.getRawValue()).subscribe({
      next: () => {
        this.loading = false;
        this.message = 'Cadastro realizado com sucesso!';
        setTimeout(() => this.router.navigate(['/auth/login']), 1500);
      },
      error: () => {
        this.loading = false;
        this.message = 'Erro ao registrar, tente novamente.';
      }
    });

}
}