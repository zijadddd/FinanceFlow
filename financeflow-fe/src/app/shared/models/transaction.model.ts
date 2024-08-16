export class TransactionResponse {
  accountName: string;
  description: string;
  expense: string;
  amount: number;
  currencyCode: string;
  defaultAmount: number;
  defaultCurrencyCode: string;
  processedAt: string;
}

export class TransactionRequest {
  description: string;
  expense: string;
  amount: number;
  accountId: number;
}
