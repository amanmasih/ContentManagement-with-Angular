const createCard = (card) => {
    const template = `
    <div class="shadow-sm tc-page-board-list-lane-card">
        <div class="tc-page-board-lane-card">
            ${card.cardTitle}
        </div>
    </div>`;
    return template;
};

const createList = (list) => {
    const template = `
    <div class="tc-page-board-lane-wrapper">
        <div class="tc-page-board-lane">
            <div class="tc-page-board-lane-title">
                ${list.listTitle}
            </div>
            ${list.cards.map(createCard).join('')}
        </div>
    </div>`;
    return template;

};

const boardRenderer = (board) => {
    const boardTitleElement = document.getElementById('tc-page-board-title');
    boardTitleElement.innerHTML = board.boardTitle;
    const template = board.lists.map(createList).join('');
    const lanesWrapper = document.getElementById('tc-page-board-lanes-wrapper');
    lanesWrapper.innerHTML += template;
};

export default boardRenderer;