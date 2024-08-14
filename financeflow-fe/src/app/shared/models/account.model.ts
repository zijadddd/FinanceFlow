export class AccountResponse {
    id: number;
    name: string;
    balance: number;
    currencyCode: string;
    createdAt: string;
    defaultBalance: number;
    defaultCurrencyCode: string;
}

export class AccountRequest {
    name: string;
    balance: number;
    currencyCode: string;
    defaultAccountId: number;
}