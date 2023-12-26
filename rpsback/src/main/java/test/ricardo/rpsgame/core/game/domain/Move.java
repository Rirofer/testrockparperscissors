package test.ricardo.rpsgame.core.game.domain;

public enum Move {

	ROCK {

		@Override
		boolean beats(Move move) {
			return SCISSORS == move;
		}

	},
	PAPER {

		@Override
		boolean beats(Move move) {
			return ROCK == move;
		}

	},
	SCISSORS {

		@Override
		boolean beats(Move move) {
			return PAPER == move;
		}
	};

	abstract boolean beats(Move move);

}
