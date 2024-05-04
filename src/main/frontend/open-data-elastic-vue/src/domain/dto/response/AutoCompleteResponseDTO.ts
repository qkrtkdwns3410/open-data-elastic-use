/**
 *packageName    :
 * fileName       : AutoCompleteResponseDTO
 * author         : ipeac
 * date           : 24. 5. 5.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 5.        ipeac       최초 생성
 */
class AutoCompleteResponseDTO {
    private readonly _movieNm: string;

    private constructor(movieNm: string) {
        this._movieNm = movieNm;
    }

    get movieNm(): string {
        return this._movieNm;
    }

    public static of(movieNm: string): AutoCompleteResponseDTO {
        return new AutoCompleteResponseDTO(movieNm);
    }
}

export {AutoCompleteResponseDTO}