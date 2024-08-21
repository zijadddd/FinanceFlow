import { WhichAction } from './which-action.model';

export interface ActionWithVisibility {
  action: WhichAction;
  isVisible?: boolean;
}
